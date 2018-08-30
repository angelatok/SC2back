package sg.com.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.account.request.LoginRequest;
import sg.com.account.request.SignUpRequest;
import sg.com.account.respond.JwtAuthenticationResponse;
import sg.com.account.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class UserPublicController {
	@Autowired AuthenticationManager authenticationManager;
	@Autowired JwtTokenProvider tokenProvider;
	@Autowired UserService service;

	
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
	/*@PostMapping("/adduser")
	public ResponseEntity<Void> addUser(@Valid @RequestBody SignUpRequest request){
		
		boolean status = service.addUser(request);
		if(status){
			return ResponseEntity.status(HttpStatus.OK).build();
		}else{
			return ResponseEntity.status(HttpStatus.IM_USED).build();

		}
	}*/
	@PostMapping("/signup")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
	
		
		if(service.addUser(signUpRequest)){
			return ResponseEntity.status(HttpStatus.OK).build();

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		

		Authentication authentication = authenticationManager.authenticate(
										new UsernamePasswordAuthenticationToken(
												loginRequest.getUsername(), 
												loginRequest.getPassword()));
		
		System.out.println(" authentication " + authentication.getName());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.gernateToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
	/*@GetMapping(value="/logout")
	public String logoutPage () {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	       // new SecurityContextLogoutHandler().logout(request, response, auth);
	        SecurityContextHolder.getContext().setAuthentication(null);
	        SecurityContextHolder.clearContext();

	    }
	    
	    
	    return "logout success";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	 */
	/*@PostMapping(value = {"/logout"})
	public String logoutDo(HttpServletRequest request,HttpServletResponse response){
	HttpSession session= request.getSession(false);
	    SecurityContextHolder.clearContext();
	         session= request.getSession(false);
	        if(session != null) {
	            session.invalidate();
	        }
	        for(Cookie cookie : request.getCookies()) {
	            cookie.setMaxAge(0);
	        }

	    return "logout";
	}*/
}
