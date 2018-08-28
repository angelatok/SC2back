package sg.com.account;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class UserModel {
	@Id
	private String id;  //ic number
	private String name;
	private String designation;
	private String email;
	private String organization;
	private String imgurl;
	private int hp; 
	@CreatedDate
	private Date createdDate;
	
	public UserModel(){
		super();
	}
	public UserModel(String name, String id, String designation, String email, String organization, String imgurl, int hp) {
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
