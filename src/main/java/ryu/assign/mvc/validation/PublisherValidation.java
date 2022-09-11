package ryu.assign.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PublisherValidation implements ConstraintValidator<PublisherCheck, String>{
	
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches("\\w+");
    }
}
