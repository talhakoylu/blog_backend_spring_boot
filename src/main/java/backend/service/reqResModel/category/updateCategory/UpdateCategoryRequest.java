package backend.service.reqResModel.category.updateCategory;

import backend.core.validations.UUIDValidation.UUIDValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {

    @NotEmpty
    @NotBlank
    @UUIDValidation
    private String id;

    @Size(min = 3, max = 100)
    private String title;

    @Size(min = 15, max = 500)
    private String description;

    private String slug;

    @Size(min = 3, max = 60)
    private String seoTitle;

    @Size(min = 15, max = 250)
    private String seoDescription;

    @Size(min = 3, max = 40)
    private String seoTags;

    @Valid
    private UpdateCategoryRequestImageModel coverImage;
}
