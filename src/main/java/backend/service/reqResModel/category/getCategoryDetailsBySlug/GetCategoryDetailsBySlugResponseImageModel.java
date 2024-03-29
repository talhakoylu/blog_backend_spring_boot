package backend.service.reqResModel.category.getCategoryDetailsBySlug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryDetailsBySlugResponseImageModel {

    private String imagePath;

    private String altText;

    private List<GetCategoryDetailsBySlugResponseOptimizedImageModel> resizedImages;

}
