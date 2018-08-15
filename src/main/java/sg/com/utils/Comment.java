package sg.com.utils;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.auditing.DateTimeProvider;

import lombok.Data;
import sg.com.audit.Audit;

@Data
public class Comment {
	//@CreatedBy
	private String createdBy;
	//@CreatedDate
	private DateTimeProvider createDate;
	
	private String comment;
	
	
	public Comment( String comment) {
		super();
		this.comment = comment;
	}
	
	
	
}
