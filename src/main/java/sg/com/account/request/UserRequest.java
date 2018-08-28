package sg.com.account.request;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;


@Data
public class UserRequest {
	@NotNull
	private String id;  //ic number
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
	
	private String imgurl; //photo
	
	public UserRequest(){
		super();
	}
	public UserRequest(String name, String id, String designation, String email, String organization, String imgurl, int hp) {
		super();
		this.name = name;
		this.id = id;
		this.designation = designation;
		this.email = email;
		this.organization = organization;
		this.imgurl = imgurl;
		this.hp = hp;
	}
	

}
