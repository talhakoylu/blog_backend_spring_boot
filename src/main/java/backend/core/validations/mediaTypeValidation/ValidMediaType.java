package backend.core.validations.mediaTypeValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MediaTypeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMediaType {

    String value() default MediaType.ALL_VALUE;

    String message() default "Unacceptable type of file.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}