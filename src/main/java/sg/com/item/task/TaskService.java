package sg.com.item.task;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.com.item.IItemRepos;
import sg.com.item.Item;
import sg.com.item.entity.CheckList;
import sg.com.item.entity.Comment;
import sg.com.item.task.request.CommentRequest;
import sg.com.item.task.request.MemberRequest;
import sg.com.item.task.request.TaskRequest;
import sg.com.item.task.request.CheckListRequest;
 
@Service
public class TaskService {
	@Autowired IItemRepos repo;

	public String createTask(TaskRequest request){
		Item item = new Item();
		item.setTitle(request.getTitle());
		item.setReceiver(request.getReceiver());
		item.setLocation(request.getLocation());
		item.setRefid(request.getRefid());
		item.setChecklist(request.getChecklist());
		item.setDueDate(item.getDueDate());
		Item saveItem = repo.save(item);
		return saveItem.getId();
	}
	
	public List<Comment> addComment(CommentRequest request){
		String id = request.getTaskId();
		
		Item task = repo.findById(id).get(); //TODO : check if task is valid
		
		List<Comment> returnComment = task.getComments();
		Comment newComment = new Comment(request.getComment());
		newComment.setCreateDate(Date.from(Instant.now()));
		//TODO : Use spring security to get the context information
		newComment.setCreatedBy("SomeBody");
		returnComment.add(newComment);
		
		repo.save(task);
		returnComment = task.getComments();
		return returnComment;
	}
	public void addMember(MemberRequest request){
		String id = request.getTaskId();
		List<String> newReceiver = request.getUsers();
		
		Item task = repo.findById(id).get(); //TODO : check if task is valid

		List<String> receiver = task.getReceiver();
		//TODO: check if membe is valid within Workspace before add.
		for(String memberid : newReceiver){
			if(!(receiver.contains(memberid))){
				System.out.println(" adding new member " + memberid);
				receiver.add(memberid);
			}
		}
		System.out.println(" reveiver list " + receiver.toString());
	//	task.setReceiver(receiver);
		repo.save(task);
		
	}
	public List<CheckList> updateCheckList(CheckListRequest request){
		String id = request.getTaskId();
		List<CheckList> checklist = request.getChecklists();
		Item task = repo.findById(id).get(); 
		task.setChecklist(checklist);
		repo.save(task);
		return repo.findById(id).get().getChecklist();
	}
}
