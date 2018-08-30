package sg.com.account;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.account.request.LoginRequest;
import sg.com.account.request.SignUpRequest;
import sg.com.account.respond.JwtAuthenticationResponse;
import sg.com.account.security.JwtTokenProvider;



@RestController
@RequestMapping("/account")
public class UserSecureCrontroller {
	
	
	@Autowired UserService service;

	
	/**
	 * 
	 * @return HttpStatus 200 OK with a list of user
	 */
	@GetMapping
	@PreAuthorize("hasRole('USER')")
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
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserModel> getUser(@PathVariable("userid") String ic){
		if(service.isIcExists(ic)){
			Optional<UserModel> result = service.getUserByIc(ic);
			return ResponseEntity.accepted().body(result.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		
	}
	
	
	/**
	 * 
	 * @param icNumber 	User IC
	 * @return		HttpStatus 202 Accepted:  remove user from db 
	 *				HttpStatus 404 not found.
	 */
	@DeleteMapping("/{userid}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Void> deleteUser(@PathVariable("userid") String icNumber){
		if(service.deleteUser(icNumber)){
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
