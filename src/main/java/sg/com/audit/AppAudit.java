package sg.com.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AppAudit implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if(auth == null){
			  return Optional.of("anonymous");
		  }
		  String username = SecurityContextHolder.getContext().getAuthentication().getName();
		  //String username = "Somebody";
		  return Optional.of(username);
		   

	}

}
