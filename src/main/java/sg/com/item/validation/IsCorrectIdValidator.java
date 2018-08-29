package sg.com.item.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.item.ItemService;

public class IsCorrectIdValidator implements ConstraintValidator<IsCorrectId, String >{

	@Autowired ItemService service;

	
	@Override
	public void initialize(IsCorrectId constraintAnnotation){}

	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		/*context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("id not available" + id).addConstraintViolation();
		*/
		return service.isfound(id);
	}


}
