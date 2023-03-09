package backend.service.businessRules;

import backend.core.utils.exceptions.BusinessRuleException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ImageBusinessRules {

    public void checkImageTypeCorrect(String contentType) {
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/jpg") &&
                !contentType.equals("image/png") && !contentType.equals("image/gif")) {
            throw new BusinessRuleException("An unacceptable file type! The image must be in JPEG/JPG, PNG or GIF.");
        }
    }

}
