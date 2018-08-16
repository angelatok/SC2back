package sg.com.item.request;

import lombok.Data;

@Data
public class CommentRequest {
	private String taskId;
	private String comment;
}
