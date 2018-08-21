package sg.com.item.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	private String createdBy;
	private Date createDate;
	
	private String comment;
	
	
	public Comment( ) {
		super();
	}


	public Comment(String createdBy, Date createDate, String comment) {
		super();
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.comment = comment;
	}
	
	
	
}
