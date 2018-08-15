package sg.com.item.requset;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.Data;
import sg.com.utils.CheckList;

@Data
public class Task {

	private String title;
	private List<String> receiver; 	//ic
	private GeoJsonPoint location;
	private String refid;			// ref id of incident or issue or task 
	private List<CheckList> checklist;
	private Date duedate;

}
