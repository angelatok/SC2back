package sg.com.item.task;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.item.IItemRepos;
import sg.com.item.Item;
import sg.com.item.entity.CheckList;
import sg.com.item.entity.Comment;
import sg.com.item.task.request.CheckListRequest;
import sg.com.item.task.request.CommentRequest;
import sg.com.item.task.request.MemberRequest;
import sg.com.item.task.request.TaskRequest;


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
	@GetMapping("/comments/{tid}/{skipSize}/{pageSize}")
	public List<Item> getComment(@PathVariable("tid") String id,
									@PathVariable("skipSize") Integer skipSize,
									@PathVariable("pageSize") Integer pageSize){
		
		System.out.println("page number  = " + skipSize);
		System.out.println("page  size    = " + pageSize);
		/*List<Item> commentList = repos.findComment(pageNumber, pageSize, id);
		int i = 0;
		for(Comment c : commentList){
			System.out.println(i++ + " Comments : " + c.toString());
		}*/
		return repos.findComment( skipSize, pageSize, id);
	}
	
	@PostMapping("/createTask")
	public String createTask(@Valid @RequestBody TaskRequest request){
		return service.createTask(request);
		/*Eg Expected Json File from client
		 {
			"title": "Peter",
			"receiver": ["S1234567A"],
			"location": null,
			"refid": "",
			"checklist": [{"description":"task 1", "done":true}],
			"duedate": ""
}
		 */
		
	}
	
	@PostMapping("/addCommnet")
	public List<Comment> addCommnet(@Valid @RequestBody CommentRequest request){
		return service.addComment(request);
		/* Eg Expected Json File from client
		  {
			"taskId": "5b74e607b684b82decc6a793",
			"comment": "new comment added via postman"
			}
		 */
		
	}
	@PostMapping("addMember")
	public void addMember(@RequestBody MemberRequest request){
		service.addMember(request);
		/* Eg Expected Json File from client
		  {
			"taskId": "5b74e607b684b82decc6a793",
			"users": ["S123456C", "S1234567D"]
			}
		 */
	}

	@PostMapping("updateCheckList")
	public List<CheckList> updateCheckList(@RequestBody CheckListRequest request){
		return service.updateCheckList(request);
		/* Eg Expected Json File from client
		  {
			"taskId": "5b74e607b684b82decc6a793",
			"checklists": [{"description":"update something", "done":false},
						   {"description":"update something", "done":true}]
			}
		 */
	}

}
