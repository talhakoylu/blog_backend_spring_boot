package backend.service.reqResModel.category;

import backend.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryResponse {

    private String title;

    private String description;

    private String slug;

    private String imagePath;

    private String imageAltText;

}
