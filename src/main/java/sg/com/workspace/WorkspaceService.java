package sg.com.workspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.com.workspace.request.WorkspaceRequest;

@Service
public class WorkspaceService {
	@Autowired IWorkspaceRepo repos;
	
	
	public List<WorkspaceModel> geAlltWorkspace(){
		return repos.findAll();
	}
	
	public Optional<WorkspaceModel> getWorkspaceById(String id){
		return repos.findById(id);
	}
	public List<WorkspaceModel> getWorkspaceByName(String name){
		return repos.findByWsName(name);
	}
	
	public WorkspaceModel createWorkspace(WorkspaceRequest request ){
	
		WorkspaceModel ws = new WorkspaceModel();
		ws.setWsName(request.getWsName());
		List<String> memberList  = new ArrayList<String>();

		for(String member : request.getUserId()){
			memberList.add(member);
			//TODO: if memeber is not found in userDB ?? reject the who create?
		}
		ws.setUserId(memberList);
		System.out.println(" ws created date " + ws.getCreatedDate());
		repos.save(ws);
		return ws;
		
	}
	public boolean updateWorkspace(WorkspaceRequest request){
		if(repos.findById(request.getId()).isPresent()){
			WorkspaceModel wsm = repos.findById(request.getId()).get();
			wsm.setWsName(request.getWsName());
			wsm.setUserId(request.getUserId());
			repos.save(wsm);
			return true;
		}
		return false;
	}
	public boolean deleteWorkspace(String wsid)
	{
		Optional<WorkspaceModel> ows = repos.findById(wsid);
		if(ows.isPresent()){
			repos.deleteById(wsid);
			return true;			
		}
		return false;

	}
	public boolean deleteUserFromWorkspace(String userId, String wsId){
		Optional<WorkspaceModel> ows = repos.findById(wsId);
		if(ows.isPresent()){
			List<String> member = ows.get().getUserId();
			if(member.contains(userId)){
				member.remove(userId);
				repos.save(ows.get());
				return true;
			}	
		}
		return false;
		
	}
	
	public List<WorkspaceModel> getUserWorkspace(String id){
		
		List<String> members = new ArrayList<String>();
		members.add(id);
		return repos.findByUserIdIn(members);
	}

}
