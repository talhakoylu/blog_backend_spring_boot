package backend.service.reqResModel.image;

import backend.core.validations.mediaTypeValidation.ValidMediaType;
import backend.core.validations.mediaTypeValidation.ValidMediaNotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateImageRequest {

    @NotNull
    @ValidMediaNotNull
    @ValidMediaType
    private MultipartFile image;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 200)
    private String altText;

    private Boolean isActive;

}
