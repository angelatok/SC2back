package sg.com.item.request;

import java.util.List;

import lombok.Data;
import sg.com.item.entity.CheckList;
import sg.com.item.validation.IsCorrectId;

@Data
public class CheckListRequest {
	@IsCorrectId
	private String taskId;
	private List<CheckList> checklists;
}
