package sg.com.workspace.validate;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.workspace.WorkspaceModel;
import sg.com.workspace.WorkspaceService;

public class IsUniqueWsNameValidator implements ConstraintValidator<IsUniqueWsName, String >{

	@Autowired WorkspaceService service;

	
	@Override
	public void initialize(IsUniqueWsName constraintAnnotation){}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		
		List<WorkspaceModel> ws = service.getWorkspaceByName(name); 
		if (ws.isEmpty()){
			return true;
		}
		
		return false;
	}


}
