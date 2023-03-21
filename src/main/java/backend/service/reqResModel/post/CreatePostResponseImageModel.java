package backend.service.reqResModel.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponseImageModel {

    private String title;

    private String imagePath;

    private String altText;

    private List<CreatePostResponseResizedImagesModel> resizedImages;

}
