package sg.com.account.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.com.account.UserModel;
import sg.com.account.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		UserModel user =  service.getUserByName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with : " + userName));
		System.out.println("user found " + user.getName() + " " + user.getPassword());
		return user;
	}
	
	//used by JWTAuthenticationFilter
	public UserDetails loadUserById(String userName){
		UserModel user = service.getUserByName(userName)
				.orElseThrow( () -> new UsernameNotFoundException("User not found with id : " + userName));
System.out.println("user found by id " + user.getName() + user.getPassword());
		return user;
	}

}
