package sg.com.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import sg.com.account.request.SignUpRequest;
import sg.com.account.request.UpdateImageRequest;

@Log4j2
@Service
public class UserService {
	@Autowired IUserRepos repos;
	@Autowired PasswordEncoder passwordEncoder;
	
	
	public List<UserModel>getAllUsers(){
		return repos.findAll();
	}
	
	public boolean isIcExists(String ic){
		return repos.existsByIcNumber(ic);
	}
/*	public boolean isNameExists(String username){
		return repos.existsByName(username);
	}*/
	public Optional<UserModel> getUserByName(String name){
		System.out.println(this.getClass().getSimpleName() + " getUserByName : " + name);
		return repos.findByName(name);
	}
	public Optional<UserModel> getUserByIc(String ic){
		return repos.findByIcNumber(ic);
	}
	public boolean deleteUser(String ic){
		if(repos.existsByIcNumber(ic)){
			repos.deleteUserModelByIcNumber(ic);
			return true;
		}
		/*
		Optional<UserModel> option = repos.findByIcNumber(ic);
		
		if(option.isPresent()){	
			repos.deleteUserModelByIcNumber(ic);
			return true;
		}*/
		return false;

	}
	public boolean addUser(SignUpRequest request){
		String ic = request.getIc();

		if(repos.existsByIcNumber(ic)){
			log.info(" Adding user that is already present " + ic);
			return false;
		}
			
		UserModel member = new UserModel();
		member.setIcNumber(ic);
		member.setDesignation(request.getDesignation());
		member.setEmail(request.getEmail());
		member.setHp(request.getHp());

		member.setName(request.getName());
		member.setOrganization(request.getOrganization());
		member.setPassword(passwordEncoder.encode(request.getPassword()));
		member.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
		if(request.getImgurl() != null){
			member.setImgurl(request.getImgurl());
		}
		System.out.println(" New Memeber " + member.toString());
		repos.save(member);
		return true;
	}
	/*public boolean updateUserImage(UpdateImageRequest request){
		if(repos.existsByIcNumber(request.getIcNumber())){
			UserModel user = repos.findByIcNumber(request.getIcNumber()).get();
			user.setImgurl(request.getImageUr());
			repos.save(user);
			return true;
		}
		return false;
	}*/
}
