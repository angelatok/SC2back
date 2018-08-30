package sg.com.account.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import sg.com.account.validation.IsValidPassword;


@Data
public class SignUpRequest {
	@NotNull
	private String ic;  //ic number
	@NotNull
	private String name;
	@NotNull
	private String designation;
	@NotNull
	private String email;
	@NotNull
	private int hp; 
	@NotNull
	private String organization;
	@NotNull
	//@IsValidPassword
	private String password;
	// 8 char. min 1 uppercase. min 1 lowercase. min 1 digit. min 1 symbol. no whitespace
	
	private String imgurl; //photo
	
	public SignUpRequest(){
		super();
	}
	public SignUpRequest(String name, String id, String designation, String email, String organization, String imgurl, int hp) {
		super();
		this.name = name;
		this.ic = id;
		this.designation = designation;
		this.email = email;
		this.organization = organization;
		this.imgurl = imgurl;
		this.hp = hp;
	}
	

}
