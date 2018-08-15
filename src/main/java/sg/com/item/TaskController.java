package sg.com.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.workspace.Workspace;


@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired 
	ITaskRepos repos;
	

	@GetMapping
	public List<Task> getAllTask(){
		return repos.findAll();
	}
	@GetMapping("/{tid}")
	public Optional<Task> getTask(@PathVariable("tid") String id){
		return repos.findById(id);
		
	}
	@GetMapping("/ws/{wsid}")
	public List<Task> getTaskbyWsid(@PathVariable("wsid") String id){
		return repos.findByWsid(id);
	}



}
