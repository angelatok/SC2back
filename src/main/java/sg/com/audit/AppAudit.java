package sg.com.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AppAudit implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		//String username = SecurityContextHolder.getContext().getAuthentication.getName();
		String username = "Somebody";
		return Optional.of(username);
	}

}
