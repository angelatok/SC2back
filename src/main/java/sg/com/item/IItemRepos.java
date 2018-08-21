package sg.com.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IItemRepos extends MongoRepository<Item, String>{
	List<Item> findByWsid(String wsid);
	
	List<Item> findByTitle(String title);
	Optional<Item> findById(String id);
	
	@Query(value="{'id': ?2}", fields = " { comments: {$slice: [?0, ?1]} }")
	List<Item> findComment( int skip, int limit, String id);
}
 