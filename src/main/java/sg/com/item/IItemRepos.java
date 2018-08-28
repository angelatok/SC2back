package sg.com.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IItemRepos extends MongoRepository<ItemModel, String>{

	@Query(value="{'type': 'TASK'}")
	List<ItemModel> findAllTask();
	
	@Query(value="{'type': 'ISSUE'}")
	List<ItemModel> findAllIssue();
	
	@Query(value="{'type': 'INCIDENT'}")
	List<ItemModel> findAllIncident();
	
	@Query(value="{'type': 'POST'}")
	List<ItemModel> findAllPost();
	
	List<ItemModel> findByWsname(String wsname);
	
	List<ItemModel> findByTitle(String title);
	Optional<ItemModel> findById(String id);
	
	@Query(value="{'id': ?2}", fields = " { comments: {$slice: [?0, ?1]} }")
	List<ItemModel> findComment( int skip, int limit, String id);
}
 