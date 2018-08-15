package sg.com.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.com.item.entity.Item;
import sg.com.item.requset.Task;
 
@Service
public class TaskService {
	@Autowired IItemRepos repo;

	public String createTask(Task task){
		Item item = new Item();
		item.setTitle(task.getTitle());
		item.setReceiver(task.getReceiver());
		item.setLocation(task.getLocation());
		item.setRefid(task.getRefid());
		item.setChecklist(task.getChecklist());
		item.setDueDate(item.getDueDate());
		Item saveItem = repo.save(item);
		return saveItem.getId();
	}
}
