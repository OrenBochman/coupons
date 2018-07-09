package coupons.logic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coupons.UserNotFoundException;
import coupons.beans.User;
import coupons.dao.UserRepository;

@Controller
public class UsersController {
	
	@Autowired
	private UserRepository userRepository;
	
	public User create(User entity) {
		return this.userRepository.save(entity);
	}

	public User update(User entity) {
		return this.userRepository.save(entity);
	}
	
	public void deleteById(long id) {
		this.userRepository.deleteById(id);
	}
	
	public Iterable<User> getAll() {
		return this.userRepository.findAll();
	}
	
	public User getById(long id) throws UserNotFoundException {
		Optional<User> maybeUser= this.userRepository.findById(id);		
		if (!maybeUser.isPresent())
		      throw new UserNotFoundException("id-"+id);
		return maybeUser.get();
	}

	public User getByName(String name) {
		User res = this.userRepository.findByName(name);
		return res;
	}

	public boolean isLogin(String name, String password) {
		// TODO Auto-generated method stub
		return true;
	}

}
