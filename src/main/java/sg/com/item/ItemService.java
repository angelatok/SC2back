package sg.com.item;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import sg.com.account.UserService;
import sg.com.item.entity.CheckList;
import sg.com.item.entity.Comment;
import sg.com.item.entity.ETopicType;
import sg.com.item.request.CheckListRequest;
import sg.com.item.request.CommentRequest;
import sg.com.item.request.ItemRequest;
import sg.com.item.request.MemberRequest;
 
@Service
public class ItemService {
	@Autowired IItemRepos repos;
	@Autowired UserService accService;

	
	public List<ItemModel> getALL(){
		return repos.findAllByOrderByModifiedDateDesc();
	}
	public List<ItemModel> getAllTask(){
		return repos.findAllTask();
	}
	public List<ItemModel> getAllIssue(){
		return repos.findAllIssue();
	}
	public List<ItemModel> getAllPost(){
		return repos.findAllPost();
	}
	public List<ItemModel> getAllIncidnt(){
		return repos.findAllIncident();
	}
	public boolean isfound(String id){	
		return repos.existsById(id);		
	}
	
	public Optional<ItemModel> getById(String id){	
		return repos.findById(id);
	}
	
	public List<ItemModel> getByWsname(String name){
		return repos.findByWsname(name);
	}
	public String createTask(ItemRequest request){
		ItemModel item = new ItemModel();
		item.setTitle(request.getTitle());
		item.setReceiver(request.getReceiver());
		item.setLocation(request.getLocation());
		item.setRefid(request.getRefid());
		item.setChecklist(request.getChecklist());
		item.setDueDate(item.getDueDate());
		String rxtype = request.getType();
		
		ETopicType rxType = ETopicType.valueOf(request.getType());
		item.setType(rxType);
		
		
		ItemModel saveItem = repos.save(item);
		System.out.println("Item save " + saveItem.toString());
		return saveItem.getId();
	}
	public List<ItemModel> findComment(int skip, int limit, String id){
		return repos.findComment( skip, limit, id);
		
	}
	public List<Comment> addComment(CommentRequest request){
		String id = request.getTaskId();
		String commentToAdd = request.getComment();
		
		ItemModel task = repos.findById(id).get(); 
	
		List<Comment> commentList = task.getComments();
		if (commentList == null ){
			commentList = new ArrayList<Comment>();
		}
		
		Comment newComment = new Comment();
		newComment.setCreateDate(Date.from(Instant.now()));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null){
		  newComment.setCreatedBy("anonymous");
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		newComment.setCreatedBy(username);
		newComment.setComment(commentToAdd);
		
		commentList.add(newComment);
		task.setComments(commentList);
		repos.save(task);
		commentList = task.getComments();
		System.out.println(" return all comments from add service " + commentList.toString());
		return commentList;
	}
	
	public boolean addMember(MemberRequest request){
		String requestId = request.getTaskId();
		List<String> requestReceiver = request.getUsers();
		
		ItemModel task = repos.findById(requestId).get(); 
		List<String> receiver = task.getReceiver();
		//TODO: check if membe is valid within Workspace before add.
		
		for(String icNumber : requestReceiver){
			/*Optional<UserModel> userlist = accService.getUserByIc(icNumber);
			if(!userlist.isPresent()){
				return false; // Error trying to add user that is not available in userdb
			}*/
			
			if(accService.isIcExists(icNumber)){
				if(!(receiver.contains(icNumber))){ // not already added
					receiver.add(icNumber);
				}
			}else{
				return false;
			}
			/*if(!(receiver.contains(icNumber))){
				receiver.add(icNumber);
			}*/
		}
		task.setReceiver(receiver);
		repos.save(task);
		return true;
		
	}

	public List<CheckList> updateCheckList(CheckListRequest request){
		String id = request.getTaskId();
		List<CheckList> checklist = request.getChecklists();
		ItemModel task = repos.findById(id).get(); 
		task.setChecklist(checklist);
		repos.save(task);
		return repos.findById(id).get().getChecklist();
	}
}
