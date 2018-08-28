package sg.com.workspace;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document 
public class WorkspaceModel {
	@Id
	private String id;	
	
	private String wsName;
	private List<String> userId;
	
	@CreatedBy
	private String createdBy; // ic
	@CreatedDate
	private Date createdDate;
	@LastModifiedDate
	private Date modifiedDate;
	
	public WorkspaceModel(String wsName, List<String> userId, String creatorId) {
		super();
		this.wsName = wsName;
		this.userId = userId;
		this.createdBy = creatorId;
	}

	
}
