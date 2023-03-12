
package backend.core.validations.mediaTypeValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class MediaTypeValidator implements ConstraintValidator<ValidMediaType, MultipartFile> {

    private final List<String> SUPPORTED_MEDIA_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif", "application/pdf");


    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        return value != null && !value.isEmpty() && SUPPORTED_MEDIA_TYPES.contains(value.getContentType());
    }

}