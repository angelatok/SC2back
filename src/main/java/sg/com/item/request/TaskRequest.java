package sg.com.item.request;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.Data;
import sg.com.item.entity.CheckList;

@Data
public class TaskRequest {

	private String title;
	private List<String> receiver; 	//ic
	private GeoJsonPoint location;
	private String refid;			// ref id of incident or issue or task 
	private List<CheckList> checklist;
	private Date duedate;

}
