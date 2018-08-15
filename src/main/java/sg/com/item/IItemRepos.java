package sg.com.item;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IItemRepos extends MongoRepository<Item, String>{
	List<Item> findByWsid(String wsid);
	
	List<Item> findByTitle(String title);
}
