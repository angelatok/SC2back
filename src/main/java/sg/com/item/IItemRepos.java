package sg.com.task;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITaskRepos extends MongoRepository<Task, String>{
	List<Task> findByWsid(String wsid);
	
	List<Task> findByTitle(String title);
}
