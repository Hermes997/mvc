package ryu.assign.mvc.validation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PublisherValidation.class)
public @interface PublisherCheck {

	String message() default "잘못된 publisher입니다.";
	
	@SuppressWarnings("rawtypes")
	Class[] groups() default {};
	
	@SuppressWarnings("rawtypes")
	Class[] payload() default {};
}
