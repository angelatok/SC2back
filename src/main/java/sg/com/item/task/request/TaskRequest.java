package sg.com.item.task.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.Data;
import sg.com.item.entity.CheckList;
import sg.com.item.task.validation.IsCorrectType;

@Data
public class TaskRequest {

	@NotNull
	@Size(min=3, message="Title should have atleast 3 characters")
	private String title;
	private List<String> receiver; 	//ic
	private GeoJsonPoint location;
	private String refid;			// ref id of incident or issue or task 
	private List<CheckList> checklist;
	private Date duedate;
	@IsCorrectType
	@Size(min=4, message="TYPE, INCIDENT, ISSUE and POST")
	private String type;

}
