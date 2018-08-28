package sg.com.account.request;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import sg.com.account.IAccountRepos;
import sg.com.account.UserModel;

@Log4j2
@Service
public class AccountService {
	@Autowired 
	IAccountRepos repos;
	public List<UserModel>getAllUsers(){
		return repos.findAll();
	}
	public Optional<UserModel> getUser(String id){
		return repos.findById(id);

	}
	public boolean deleteUser(String id){
		Optional<UserModel> option = repos.findById(id);
		
		if(option.isPresent()){	
			repos.deleteById(id);
			return true;
		}
		return false;

	}
	public boolean addUser(UserRequest request){
		String id = request.getId();

		if(repos.findById(id).isPresent()){
			//TODO log user already exist.
			log.info(" Adding user that is already present " + id);
			return false;
		}
			
		UserModel member = new UserModel();
		member.setId(id);
		member.setDesignation(request.getDesignation());
		member.setEmail(request.getEmail());
		member.setHp(request.getHp());

		member.setName(request.getName());
		member.setOrganization(request.getOrganization());
		
		if(request.getImgurl() != null){
			member.setImgurl(request.getImgurl());
		}
		System.out.println(" New Memeber " + member.toString());
		repos.save(member);
		return true;
	}
}
