
package backend.core.validations.mediaTypeValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class MediaTypeValidator implements ConstraintValidator<ValidMediaType, MultipartFile> {

    private String allowed;

    @Override
    public void initialize(ValidMediaType constraintAnnotation) {
        allowed = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value != null && allowed.equals(MediaType.ALL_VALUE) && allowed.equals(value.getContentType());
    }

}