
package backend.core.validations.UUIDValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UUIDValidator implements ConstraintValidator<UUIDValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("buraya bakarlar");
        return value != null &&
                Pattern.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", value);
    }

}