package sg.com.item.validation;

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
@Constraint(validatedBy = {IsCorrectTypeValidator.class})
public @interface IsCorrectType {
	String message() default "Type is incorrect";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
