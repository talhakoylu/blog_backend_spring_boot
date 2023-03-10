package backend.core.validations.UUIDValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UUIDValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UUIDValidation {

    String message() default "Not acceptable parameter.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}