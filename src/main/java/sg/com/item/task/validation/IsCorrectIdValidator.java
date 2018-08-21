package sg.com.item.task.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.item.IItemRepos;
import sg.com.item.Item;

public class IsCorrectIdValidator implements ConstraintValidator<IsCorrectId, String >{

	@Autowired IItemRepos repo;

	
	@Override
	public void initialize(IsCorrectId constraintAnnotation){}

	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		if(id == null) return false;
		
		Optional<Item> task = repo.findById(id); 
		if (task.isPresent()){
			return true;
		}
		
		return false;
	}


}
