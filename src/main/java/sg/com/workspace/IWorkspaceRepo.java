package sg.com.workspace;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IWorkspaceRepo extends MongoRepository<Workspace, String> {
	
	@Override
	List<Workspace> findAll();
	
	@Override
	Optional<Workspace> findById(String id);
	
	List<Workspace> findByUserIdIn(List<String> members);
	
	
}
