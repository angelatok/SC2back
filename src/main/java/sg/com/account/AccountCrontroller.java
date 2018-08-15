package sg.com.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/account")
public class AccountCrontroller {
	
	@Autowired 
	IAccountRepos repos;
	

	@GetMapping("/users")
	public List<User> getAllUsers(){
		return repos.findAll();
	}
	
	@GetMapping("/users/{userid}")
	public Optional<User> getUser(@PathVariable("userid") String id){
		return repos.findById(id);
	}
	
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user){
		User savedUser = repos.save(user);
		return savedUser.getId();
	}


	


}
