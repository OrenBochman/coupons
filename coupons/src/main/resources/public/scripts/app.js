(function() {	

	function formatDate(date){
		var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();

		if (month.length < 2) month = '0' + month;
		if (day.length < 2) day = '0' + day;

		return [year, month, day].join('-');
	}

	// Defining the application
	var app = angular.module('myApp', ['ngRoute']);

	app.config(function($routeProvider) {
		$routeProvider
		.when("/home", {
			templateUrl : "index.html"
		})
		.when("/products", {
			templateUrl : "products.html",
			controller	: "ProductsController",
		})
		.when("/bids", {
			templateUrl : "bids.html",
			controller	: "BidsController"
		})
		.when("/users", {
			templateUrl : "users.html",
			controller	: "UsersController"
		});
	})

	// Register the controller's function
	app.controller('UsersController', ['$http', '$scope', function($http, $scope){

		$scope.getAllUsers = function(){
			$http.get('/api/users').then(	// Success function			
				function(response){console.log($scope.users=response.data)},
				function(data){	console.log($scope.error=resp.data);// Failure function
			});
		};

		$scope.createUser = function(){
			//create a password expiration data
			var future = new Date();		
			var n = formatDate(future);
			$scope.user.passwordExpiration=n;

			// Sending a post message to the API layer in the server 
			$http.post('/api/users', $scope.user).then(
				function(response){	console.log(response)},
				function(data){console.log(data);});
		};
	}])

	// Register the controller's function
	app.controller('ProductsController', ['$http', '$scope', function($http, $scope){
		$scope.tableData = {};
		$scope.editingData = {};

		$scope.getAllProducts = function(){
			$http.get('/api/products').then(
					function(response){// Success function		
						$scope.tableData=response.data
						console.log('get',response)
						// init editing flags
						for (var i = 0, length = $scope.tableData.length; i < length; i++) {
							$scope.editingData[$scope.tableData[i].id] = false;
						}
						console.log($scope.editingData)

					},					
					function(data){// Failure function
						$scope.error=resp.data
						console.log('fail: '+ resp);
					});
		};

		$scope.modify = function(id){
			$scope.editingData[id]=true;
		};

		$scope.update = function(product){
			$http.put('/api/products', product).then(
					function(response){// Success function
						console.log('put',response)
					},
					function(data){// Failure function
						console.log('fail: '+ data);
					})
					$scope.editingData[product.id] = false;
		};

		$scope.createProduct = function(){
			// Sending a post message to the API layer in the server 
			$http.post('/api/products', $scope.add.product).then(
					function(response){// Success function
						console.log('post',response)
						$scope.getAllProducts();
					},
					function(data){// Failure function
						console.log('fail: '+ data);
					});
		};

		$scope.deleteProduct = function(index){
			// Sending a delete message to the API layer in the server 
			$http.delete('/api/products/'+index).then(
					function(response){// Success function
						console.log('delete',response)
						$scope.getAllProducts();
					},
					function(data){// Failure function
						console.log('fail: '+ data);
					});
		};
	}])

	// Register the controller's function
	app.controller('BidsController', ['$http', '$scope', function($http, $scope){

		$scope.tableData = {};
		$scope.editingData = {};
		$scope.users = {};
		$scope.products = {};

		$scope.getAll = function(){
			//get all bids,uses and products
			$http.get('/api/products').then(							// Get all products
					function(response){console.log($scope.products=response.data) },// Success function 	
					function(data){	$scope.error=resp.data;});			// Failure function					
			$http.get('/api/users').then(								// Get all users
					function(response){console.log($scope.users=response.data)},		// Success function
					function(data){	$scope.error=resp.data;})	 		// Failure function
					$http.get('/api/bids').then(								// Get all bids
							function(response){
								console.log($scope.tableData=response.data);
								$scope.initTable();
							},													// Success function		
							function(data){$scope.error=resp.data});			// Failure function
		};

		$scope.initTable = function(){
			console.log("length");
			console.log($scope.tableData.length);
			for (var i = 0, length = $scope.tableData.length; i < length; i++) {
				$scope.editingData[$scope.tableData[i].id] = false;
			}
			console.log($scope.editingData)
		};

		$scope.modify = function(bid){
			$scope.editingData[bid.id]=true;
		};

		$scope.update = function(bid){
			$http.put('/api/bids', bid).then(
					function(response){// Success function
						console.log('put',response)
					},
					function(data){// Failure function
						console.log('fail: '+ data);
					});
			$scope.editingData[bid.id] = false;
		};

		$scope.create = function(){
			var future = new Date();		
			var n = formatDate(future);
			$scope.add.timestamp=n;
			// Sending a post message to the API layer in the server 
			$http.post('/api/bids', $scope.add.bid).then(
					function(response){// Success function
						console.log('post',response)
						$scope.getAllProducts();
					},
					function(data){// Failure function
						console.log('fail: '+ data);
					});
		};

		$scope.delete = function(id){
			// Sending a delete message to the API layer in the server 
			$http.delete('/api/bids/'+id).then(
					function(response){// Success function
						console.log('delete',response)
						$scope.getAll();
					},
					function(data){// Failure function
						console.log('fail: '+ data);
					});
		};

	}])
})();