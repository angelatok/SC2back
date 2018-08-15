package sg.com.item;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.Data;
import sg.com.utils.CheckList;
import sg.com.utils.Comment;
import sg.com.utils.EStatus;
import sg.com.utils.ETopicType;

@Data
public class Item {
	
	@Id
	private String id;
	@CreatedBy
	private String createdBy; // ic
	@CreatedDate
	private DateTimeProvider createDate;
	@LastModifiedDate
	private DateTimeProvider modifiedDate;
	
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
	
}
