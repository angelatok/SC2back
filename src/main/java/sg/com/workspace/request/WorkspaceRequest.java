package sg.com.workspace.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import sg.com.workspace.validate.IsUniqueWsName;

@Data
public class WorkspaceRequest {
	private String Id;
	@NotNull
	@IsUniqueWsName
	private String wsName;
	private List<String> userId;
}
