package sg.com.item.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.item.IItemRepos;
import sg.com.item.ItemModel;

public class IsCorrectIdValidator implements ConstraintValidator<IsCorrectId, String >{

	@Autowired IItemRepos repo;

	
	@Override
	public void initialize(IsCorrectId constraintAnnotation){}

	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		/*context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("id not available" + id)
		.addConstraintViolation();*/
		
		if(id == null) {
			return false;
		}
		
		Optional<ItemModel> task = repo.findById(id); 
		return task.isPresent();
	}


}
