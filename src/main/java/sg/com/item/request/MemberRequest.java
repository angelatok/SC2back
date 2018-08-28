package sg.com.item.request;

import java.util.List;

import lombok.Data;
import sg.com.item.validation.IsCorrectId;

@Data
public class MemberRequest {
	@IsCorrectId
	private String taskId;
	private List<String> users; // member ic
	

}
