package coupons.login;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

/**
 * Login cache - to store logged in token  and avoid cost of checking user password vs the db
 * 
 * @author Oren Bochman
 *
 * TODO: migrate to  H2 repository 
 *
 */

@Component
public class LoginCache {

	//TODO: logout users from store after 30 minutes of inactivity
	final long TIME_OUT= 30*60*1000; // 30 minutes in MS
	/** 
	 * token store
	 */
	private HashMap<String,Date> logins = new HashMap<>();
	
	public boolean isLoggedIn(String token){
		Date now = new Date();

		if (logins.containsKey(token)){ 
			if(logins.get(token).getTime() - TIME_OUT > now.getTime()){
				logins.put(token,now);	//reset logout time 
				return true;
			}else{
				logout(token);
			}		
		}
		return false;
	}

	public void login(String key){
		logins.put(key, new Date());
	}

	public void logout(String key){
		logins.remove(key);
	}
}
