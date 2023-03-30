package backend.service.reqResModel.category.getCategoryById;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryByIdResponseImageModel {

    private String title;

    private String altText;

    private String imagePath;

    private List<GetCategoryByIdResponseOptimizedImageModel> resizedImages;

}
