package sg.com.item.task.request;

import java.util.List;

import lombok.Data;
import sg.com.item.entity.CheckList;

@Data
public class CheckListRequest {
	private String taskId;
	private List<CheckList> checklists;
}
