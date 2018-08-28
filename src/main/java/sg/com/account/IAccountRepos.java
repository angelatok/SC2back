package sg.com.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAccountRepos extends MongoRepository<UserModel, String>{
	@Override
	List<UserModel> findAll();
	
	@Override
	Optional<UserModel> findById(String id);
}
