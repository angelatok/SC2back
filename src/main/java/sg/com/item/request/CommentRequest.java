package sg.com.item.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import sg.com.item.validation.IsCorrectId;

@Data
public class CommentRequest {
	@NotNull
	@IsCorrectId(message = "{Id not found}")
	private String taskId;
	@NotNull
	private String comment;
}
