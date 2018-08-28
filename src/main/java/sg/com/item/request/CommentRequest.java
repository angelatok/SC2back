package sg.com.item.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import sg.com.item.validation.IsCorrectId;

@Data
public class CommentRequest {
	@IsCorrectId
	private String taskId;
	@NotNull
	private String comment;
}
