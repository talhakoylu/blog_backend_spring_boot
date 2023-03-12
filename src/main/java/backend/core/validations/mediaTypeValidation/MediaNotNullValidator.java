
package backend.core.validations.mediaTypeValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class MediaNotNullValidator implements ConstraintValidator<ValidMediaNotNull, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }

}