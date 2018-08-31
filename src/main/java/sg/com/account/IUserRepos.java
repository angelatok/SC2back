package sg.com.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepos extends MongoRepository<UserModel, String>{
	@Override
	List<UserModel> findAll();
	
	/*@Override
	Optional<UserModel> findById(String id);
	*/
	Optional<UserModel> findByIcNumber(String ic);
	Optional<UserModel> findByName(String name);
	Optional<UserModel> findByEmail(String email);
	
	Boolean existsByIcNumber(String ic);
	//Boolean existsByName(String name);
	Long deleteUserModelByIcNumber(String ic);
	
}
