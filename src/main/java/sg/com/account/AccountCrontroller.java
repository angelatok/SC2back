package sg.com.account;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.account.request.AccountService;
import sg.com.account.request.UserRequest;


@RestController
@RequestMapping("/account")
public class AccountCrontroller {
	
	
	@Autowired AccountService service;

	/**
	 * 
	 * @return HttpStatus 200 OK with a list of user
	 */
	@GetMapping
	public List<UserModel> getAllUsers(){
		return service.getAllUsers();
	}
	
	/**
	 * @param id 	IC number of user
	 * @return 		HttpStatus 202 Accepted with the found user 
	 *				HttpStatus 404 not found
	 * 
	 */
	@GetMapping("/{userid}")
	public ResponseEntity<UserModel> getUser(@PathVariable("userid") String id){
		Optional<UserModel> result = service.getUser(id);
		if(result.isPresent()){
			return ResponseEntity.accepted().body(result.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		
	}
	
	/**
	 * 
	 *  @return 	HttpStatus 200 OK and create user 
	 * 				HttpStatus 226 IM Used if IC already found in DB.
	 *  Only imgurl can be null, the rest of the field must not be null.
	 * 
	 	{ 
			"id": "S1234567X",
			"name": "Zala",
			"designation": "admin",
			"email": "zala@gmail.com",
			"organization": "companyZ",
			"hp": "91234567"
		}
	 */
	@PostMapping("/adduser")
	public ResponseEntity<Void> addUser(@Valid @RequestBody UserRequest request){
		boolean status = service.addUser(request);
		if(status){
			return ResponseEntity.status(HttpStatus.OK).build();
		}else{
			return ResponseEntity.status(HttpStatus.IM_USED).build();

		}
	}
	
	/**
	 * 
	 * @param id 	User IC
	 * @return		HttpStatus 202 Accepted:  remove user from db 
	 *				HttpStatus 404 not found.
	 */
	@DeleteMapping("/{userid}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userid") String id){
		if(service.deleteUser(id)){
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
