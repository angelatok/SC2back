package sg.com.account.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(){
		return new JwtAuthenticationFilter();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{

		authenticationManagerBuilder.userDetailsService(customUserDetailService)
									.passwordEncoder(passwordEncoder());
		
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.cors()
				.and()
			.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			
			/*.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				//.logoutSuccessUrl("/login")
				.and()*/
			.authorizeRequests()
				.antMatchers("/", 
						"/favicon.ico",
						"/**/*.png",
						"/**/*.fig", 
						"/**/*.svg", 
						"/**/*.jpg",
						"/**/*.jpeg",
						"/**/*.css", 
						"/**/*.js")
					.permitAll()
				.antMatchers("/auth/**")
					.permitAll()
				/*.antMatchers("/api/user/checkUsernameAvailablity", "/api/user/checkEmailAvailablity")
					.permitAll()
				.antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
					.permitAll()*/
				.anyRequest()
					.authenticated();
		/*http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
		.invalidateHttpSession(true);*/
		
		
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		
			
	}
}
