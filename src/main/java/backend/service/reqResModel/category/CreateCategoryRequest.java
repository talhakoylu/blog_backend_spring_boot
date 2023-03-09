package backend.service.reqResModel.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

    @NotBlank
    @NotNull
    @Size(min = 3, max = 30)
    private String title;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 500)
    private String description;

    private String coverImageId;

}
