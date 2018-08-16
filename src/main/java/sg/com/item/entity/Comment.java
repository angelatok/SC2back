package sg.com.item.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	private String createdBy;
	private Date createDate;
	
	private String comment;
	
	
	public Comment( String comment) {
		super();
		this.comment = comment;
	}
	
	
	
}
