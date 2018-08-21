package sg.com.item.task.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import sg.com.item.task.validation.IsCorrectId;

@Data
public class CommentRequest {
	@IsCorrectId
	private String taskId;
	@NotNull
	private String comment;
}
