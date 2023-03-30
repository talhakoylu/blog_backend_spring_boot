package backend.service.reqResModel.category.getAllActiveCategories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllActiveCategoriesResponseImageModel {

    private String imagePath;

    private String altText;

    private List<GetAllActiveCategoriesResponseOptimizedImageModel> resizedImages;

}
