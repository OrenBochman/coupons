package coupons.api;

import org.springframework.web.bind.annotation.RestController;

import coupons.ApplicationException;
import coupons.beans.Company;
import coupons.logic.CompanyController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * rest API
 * @author oren
 *
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyApi {
	
	@Autowired
	CompanyController companiesController; 

	public CompanyApi() {}	
	
	@GetMapping("/{id}")
	public Company getById(@PathVariable("id") long id) {
		  Optional<Company>res= this.companiesController.get(id);
		  if( res.isPresent()) {
				  return res.get();
		  }
		  else {
			  return null; 
		  }
	}

	@GetMapping()
	public Iterable<Company> getAll() {
		return  this.companiesController
				.getAll();
	}
	
	@PostMapping
	public void create(@RequestBody Company company) {
		this.companiesController
			.put(company);
	}

	@PutMapping
	public void update(@RequestBody Company newValue) {
		this.companiesController
			.put(newValue);

	}    

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {
		companiesController.remove(id);
	}  

}




