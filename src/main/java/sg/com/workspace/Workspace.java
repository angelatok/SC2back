package sg.com.workspace;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Workspace {
	@Id
	private String id;
	private List<String> userId;
	private String creatorId;
	
	
	public Workspace(String id, List<String> userId, String creatorId) {
		super();
		this.id = id;
		this.userId = userId;
		this.creatorId = creatorId;
	}

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		id = id;
	}
	

	
	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}

	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

}
