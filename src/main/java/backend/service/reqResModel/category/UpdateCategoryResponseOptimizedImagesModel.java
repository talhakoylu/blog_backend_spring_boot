package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryResponseOptimizedImagesModel {

    private String imagePath;

    private int width;

}
