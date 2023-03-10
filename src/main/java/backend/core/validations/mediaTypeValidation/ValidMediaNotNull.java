package backend.core.validations.mediaTypeValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MediaNotNullValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMediaNotNull {

    String message() default "File field not be empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}