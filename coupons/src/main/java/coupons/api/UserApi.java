package coupons.api;

import org.springframework.web.bind.annotation.RestController;

import coupons.UserNotFoundException;
import coupons.beans.Role;
import coupons.beans.User;
import coupons.filters.LoginFilter;
import coupons.logic.UsersController;
import coupons.login.LoginCache;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/users")
public class UserApi {

	private final static Logger LOG = LoggerFactory.getLogger(UserApi.class);

	private static final byte[] SECRET = "$uNd@Nc3k1d".getBytes();

	@Autowired
	UsersController usersController;

	@Autowired
	LoginCache loginCache;
	
	@GetMapping("/{id}")
	public User getById(@PathVariable("id") long id) throws UserNotFoundException {
		LOG.info("getById:{}", id);		
		return  usersController.getById(id);
	}


	@GetMapping("byName/")
	public User getUserbyName(@RequestParam(value="name" ) String name) {
		LOG.info("getUserbyName:{}", name);		

		User res=null;
		res = usersController.getByName(name);
		return res;
	}
	//
	//	@PostMapping(value="/login")
	//	public void login(@RequestBody User user, HttpServletResponse response) {
	//		if(usersController.isLogin(user.getName(),user.getPassword())) {
	//			String token = "userId:";
	//			//encrypt token
	//			Cookie coockie = new Cookie("token", token);
	//			response.addCookie(coockie);
	//			return;
	//		}
	//	}

	@PostMapping(value = "/login", consumes = {"application/x-www-form-urlencoded"} )
	public String login(@RequestBody User login,
			//HttpServletRequest request,
			HttpServletResponse response) throws ServletException, UserNotFoundException {

		
		String jwtToken = "";
		//if good token skip login
		//request.getCookies();
		
		if (login.getName() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}
		
		String userName = login.getName();
		String password = login.getPassword();
		String email=login.getEmail();
		Role role = login.getRole();

		User user = usersController.getById(login.getId());

		if (user == null) {
			throw new ServletException("User name not found.");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = Jwts.builder()
				.setSubject(userName)
				.claim("roles", role)
				.claim("email", email)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, UserApi.SECRET)
				.compact();

		response.addCookie(new Cookie("token", jwtToken)); 	//add token as cookie to client
		loginCache.login(jwtToken);							//add token to cache


		return jwtToken;
	}

	@PostMapping
	public String createUser(@RequestBody User user) {
		this.usersController.create(user);
		return String.format("user %s added ", user.toString());
	}

	@PutMapping("/{id}")
	public String updateUser(@RequestBody User userUpdate) {
		User res= this.usersController.update(userUpdate);
		return String.format("update user %s ", res.toString());
	}    

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		usersController.deleteById(id);
	}  
}
