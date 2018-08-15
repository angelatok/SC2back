package sg.com.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.item.requset.Task;


@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired IItemRepos repos;
	@Autowired TaskService service;

	@GetMapping
	public List<Item> getAllTask(){
		return repos.findAll();
	}
	@GetMapping("/{tid}")
	public Optional<Item> getTask(@PathVariable("tid") String id){
		return repos.findById(id);
		
	}
	@GetMapping("/ws/{wsid}")
	public List<Item> getTaskbyWsid(@PathVariable("wsid") String id){
		return repos.findByWsid(id);
	}
	@PostMapping("/createTask")
	public String createTask(@RequestBody Task task){
		System.out.println(" Rx task " + task.toString());
		return service.createTask(task);
		
		
	}



}
