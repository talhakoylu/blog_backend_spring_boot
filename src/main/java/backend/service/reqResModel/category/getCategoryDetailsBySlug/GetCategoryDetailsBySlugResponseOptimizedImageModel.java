package backend.service.reqResModel.category.getCategoryDetailsBySlug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryDetailsBySlugResponseOptimizedImageModel {

    private String imagePath;

    private int width;

}
