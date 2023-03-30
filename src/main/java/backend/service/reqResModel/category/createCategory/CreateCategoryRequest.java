package backend.service.reqResModel.category.createCategory;

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
public class CreateCategoryRequest {

    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotEmpty
    @NotBlank
    @Size(min = 15, max = 500)
    private String description;

    private String slug;

    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 60)
    private String seoTitle;

    @NotEmpty
    @NotBlank
    @Size(min = 15, max = 250)
    private String seoDescription;

    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 40)
    private String seoTags;

    @UUIDValidation
    private String coverImageId;

    private Boolean isActive;
}
