package sg.com.account;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Document
@Data
public class UserModel implements UserDetails{
	@Id
	private String id;  //ic number\
	private String icNumber;
	private String name;
	private String designation;
	private String email;
	private String organization;
	private String imgurl;
	private int hp; 
	@CreatedDate
	private Date createdDate;
	
	// UserDetails
	private  Collection<? extends GrantedAuthority> authorities;
	private String password;

	
	// UserDetails 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;

	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public String getPassword() {
		return password;

	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getUsername() {
		
		return this.name;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;

	}
	@Override
	public boolean isAccountNonLocked() {
		return true;

	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;

	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
