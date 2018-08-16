package sg.com.item.task.request;

import java.util.List;

import lombok.Data;
import sg.com.account.User;

@Data
public class MemberRequest {
	private String taskId;
	private List<String> users; // member ic
	

}
