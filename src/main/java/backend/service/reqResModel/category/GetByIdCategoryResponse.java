package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetByIdCategoryResponse {

    private String title;

    private String description;

    private String slug;

    private String imagePath;

    private String imageAltText;

}
