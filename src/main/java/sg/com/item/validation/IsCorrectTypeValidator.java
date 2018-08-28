package sg.com.item.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsCorrectTypeValidator implements ConstraintValidator<IsCorrectType, String >{

	@Override
	public void initialize(IsCorrectType constraintAnnotation){}

	@Override
	public boolean isValid(String type, ConstraintValidatorContext context) {
		if(type == null) return false;
		
		if( type.equalsIgnoreCase("Task") 
				|| type.equalsIgnoreCase("Incident") 
				|| type.equalsIgnoreCase("Issue") 
				|| type.equalsIgnoreCase("Post")){
		//	context.disableDefaultConstraintViolation();
		//	context.buildConstraintViolationWithTemplate("Valid Type is : TASK/INCIDENT/ISSUE/POST");
			return true;
		}
		return false;
	}


}
