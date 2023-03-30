package backend.service.reqResModel.category.getAllActiveCategories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllActiveCategoriesResponse {

    private String title;

    private String slug;

    private GetAllActiveCategoriesResponseImageModel coverImage;

}
