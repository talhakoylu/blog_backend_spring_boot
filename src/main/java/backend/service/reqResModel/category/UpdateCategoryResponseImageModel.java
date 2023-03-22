package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryResponseImageModel {

    private String imagePath;

    private String altText;

    private List<UpdateCategoryResponseOptimizedImagesModel> resizedImages;

}
