package sg.com.item;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.item.entity.CheckList;
import sg.com.item.entity.Comment;
import sg.com.item.request.CheckListRequest;
import sg.com.item.request.CommentRequest;
import sg.com.item.request.MemberRequest;
import sg.com.item.request.ItemRequest;


@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired ItemService service;
	
	/**
	 * 
	 * @return HttpStatus 200 OK and Json file for ItemModel or empty list if not found
	 */
	@GetMapping
	public List<ItemModel> getAll(){
		return service.getALL();
	}
	
	/**
	 * 
	 * @return HttpStatus 200 OK and Json file for ItemModel or empty list if not found
	 */
	@GetMapping("/task")
	public List<ItemModel> getAllTask(){
		return service.getAllTask();
	}
	/**
	 * 
	 * @return HttpStatus 200 OK and Json file for ItemModel or empty list if not found
	 */
	@GetMapping("/issue")
	public List<ItemModel> getAllIssue(){
		return service.getAllIssue();
	}
	/**
	 * 
	 * @return HttpStatus 200 OK and Json file for ItemModel or empty list if not found
	 */
	@GetMapping("/post")
	public List<ItemModel> getAllPost(){
		return service.getAllPost();
	}
	/**
	 * 
	 * @return HttpStatus 200 OK:  Json file for ItemModel or empty list if not found
	 */
	@GetMapping("/incident")
	public List<ItemModel> getAllIncident(){
		return service.getAllIncidnt();
	}
	/**
	 * 
	 * @param id 	Identity of task/issue/post/incident
	 * @return 		HttpStatus 202 Accepted 
	 * 		 		HttpStatus 404 Not found
	 */
	@GetMapping("/{tid}")
	public ResponseEntity<ItemModel> getItem(@PathVariable("tid") String id){
		if(service.isfound(id)){
			Optional<ItemModel> result = service.getById(id);
			return ResponseEntity.accepted().body(result.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}
	/**
	 * 
	 * @param wsname	query items base on name of workspace
	 * @return 			HttpStatus 202 Accepted 
	 * 					HttpStatus 404 Not found 
	 */
	@GetMapping("/ws/{wsname}")
	public ResponseEntity<List<ItemModel>> getItembyWsid(@PathVariable("wsname") String wsname){
		if(service.getByWsname(wsname).isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
		List<ItemModel> lim = service.getByWsname(wsname);	
		return ResponseEntity.accepted().body(lim);
	}
	
	/**
	 * 
	 * @param request 	Json file of task request object.
	 * 					title: cannot be null, with min 3 character size 
	 * 					type: only accept TASK/INCIDENT/ISSUE/POST
	 * @return			HttpStatus 200 OK : id of the new created task
	 * 					HttpStatus 400 Bad Request: Type or Title error
	 * eg
	   { 
			"title": "Title Posted 1",
			"receiver": ["S1234567D"],
			"type":"TASK",
			"checklist": [{"description": "item 1", "done": false},{"description": "item 2", "done": true}]

		}
	 */
	@PostMapping
	public String createItem(@Valid @RequestBody ItemRequest request){
		return service.createTask(request);
		
	}
	
	/**
	 * 
	 * @param request 	Comment to be added to the Task/Issue/Incident/Post
	 * @return			A list of all comment current available in the udpated item
	 * 
	  	{
			"taskId": "5b84b99d5cb1d119e4223166",
			"comment": "{{$timestamp}} new comment added via postman"
		}
	 */
	@PutMapping("/comment")
	public List<Comment> addCommnet(@Valid @RequestBody CommentRequest request){
		return service.addComment(request);
		
	}
	
	/**
	 * 
	 * @param id		id of the item to query
	 * @param skipSize	number of item to skip
	 * @param pageSize	number of item to be return for each query
	 * @return	Item object with comment truncated base on skipSize and pageSize
	 * 
	 */
	@GetMapping("/comment/{tid}/{skipSize}/{pageSize}")
	public List<ItemModel> getComment(@PathVariable("tid") String id,
									@PathVariable("skipSize") Integer skipSize,
									@PathVariable("pageSize") Integer pageSize){
		
		return service.findComment( skipSize, pageSize, id);
	}
	

	/**
	 * 
	 * @param request		Json of MemberRequest object
	 * @return				Http Error 500 Internal Server Error will be thrown if Id is not current
	 * 						available in the database.
	 * 						HttpStatus 403 Forbidden when user is not available in userdb
	 * 						HttpStatus 200 OK when user is added to Task/Issue/Incident/Post.
	 * 
	 	{
			"taskId": "5b84bd315cb1d15288298c49",
			"users": ["S123456A", "S1234567B"]
		}
	 */
	@PutMapping("/member")
	public ResponseEntity<Void> addMember(@RequestBody MemberRequest request){
		if(service.addMember(request)){
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

	}

	/**
	 * 
	 * @param request 	Json for CheckListReqeustObjecct, 
	 * 					the checklist provided will be replace exiting list in db
	 * @return			List of item in the checklist.
	 */
	@PutMapping("/checklist")
	public List<CheckList> updateCheckList(@RequestBody CheckListRequest request){
		return service.updateCheckList(request);
		
	}

}
