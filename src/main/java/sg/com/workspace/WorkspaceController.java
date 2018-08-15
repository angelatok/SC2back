package sg.com.workspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {
	@Autowired 
	IWorkspaceRepo repos;
	

	@GetMapping
	public List<Workspace> getWorkspaces(){
		return repos.findAll();
	}
	@GetMapping("/{wsid}")
	public Optional<Workspace> getWorkspace(@PathVariable("wsid") String id){
		return repos.findById(id);
		
	}
	
	@GetMapping("/userid/{userId}")
	public List<Workspace> getUserWorkspace(@PathVariable("userId") String id){
		List<String> members = new ArrayList<String>();
		members.add(id);
		return repos.findByUserIdIn(members);
	}
	
}
