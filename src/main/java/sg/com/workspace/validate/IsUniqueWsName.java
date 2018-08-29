package sg.com.workspace.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsUniqueWsNameValidator.class})
public @interface IsUniqueWsName {
	String message() default "Workspace Name already used !";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
