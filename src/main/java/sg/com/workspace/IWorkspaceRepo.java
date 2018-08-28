package sg.com.workspace;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IWorkspaceRepo extends MongoRepository<WorkspaceModel, String> {
	
	@Override
	List<WorkspaceModel> findAll();
	
	List<WorkspaceModel> findByWsName(String name);
	
	@Override
	Optional<WorkspaceModel> findById(String id);
	
	List<WorkspaceModel> findByUserIdIn(List<String> members);
	
	
}
