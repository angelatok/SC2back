package sg.com.item.task;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.com.item.IItemRepos;
import sg.com.item.Item;
import sg.com.item.entity.Comment;
import sg.com.item.task.request.CommentRequest;
import sg.com.item.task.request.TaskRequest;
 
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
		
		Item task = repo.findById(id).get();
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
}
