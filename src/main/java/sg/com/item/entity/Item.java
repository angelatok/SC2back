package sg.com.item.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.Data;

@Data
public class Item {
	
	@Id
	private String id;
	@CreatedBy
	private String createdBy; // ic
	@CreatedDate
	private Date createdDate;
	@LastModifiedDate
	private Date modifiedDate;
	
	private String title;
	private String detail;
	private GeoJsonPoint location;
	private String wsid;
	private ETopicType type;
	
	
	private List<String> receiver; //ic
	private EStatus status; // New, Doing, Done
	private Date DueDate;
	private String refid;	// ref id of incident or issue or task 
	
	private List<String> assignees; //  ic of accountuser
	private List<CheckList> checklist;
	private List<Comment> comments;
	private EStatus levelOfIncident; // HML
	
	
	public Item() {
		super();
		
	}
	
	
	
}
