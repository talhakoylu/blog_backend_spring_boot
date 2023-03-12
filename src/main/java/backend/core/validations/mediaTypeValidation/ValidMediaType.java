package backend.core.validations.mediaTypeValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MediaTypeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMediaType {

    String message() default "Unacceptable type of file.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}