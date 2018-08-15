package sg.com.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAccountRepos extends MongoRepository<User, String>{
	@Override
	List<User> findAll();
	
	@Override
	Optional<User> findById(String id);
}
