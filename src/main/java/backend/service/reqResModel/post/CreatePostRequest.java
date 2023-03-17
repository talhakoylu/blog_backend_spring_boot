package backend.service.reqResModel.post;

import backend.core.validations.UUIDValidation.UUIDValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    @NotBlank
    @NotEmpty
    @Size(min = 10, max = 150)
    private String title;

    @NotEmpty
    @NotBlank
    @Size(min = 30)
    private String content;

    private String slug;

    @NotEmpty
    @NotBlank
    @Size(min = 10, max = 100)
    private String seoTitle;

    @NotEmpty
    @NotBlank
    @Size(min = 30, max = 255)
    private String seoDescription;

    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 30)
    private String seoTags;
    
    private String postStatus;

    @UUIDValidation
    private String categoryId;

    @UUIDValidation
    private String coverImageId;

}
