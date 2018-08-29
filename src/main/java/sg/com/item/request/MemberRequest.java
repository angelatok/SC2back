package sg.com.item.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import sg.com.item.validation.IsCorrectId;

@Data
public class MemberRequest {
	@NotNull
	@IsCorrectId(message = "{Id not found}")
	private String taskId;
	private List<String> users; // member ic
	

}
