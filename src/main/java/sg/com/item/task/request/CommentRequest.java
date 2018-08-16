package sg.com.item.task.request;

import lombok.Data;

@Data
public class CommentRequest {
	private String taskId;
	private String comment;
}
