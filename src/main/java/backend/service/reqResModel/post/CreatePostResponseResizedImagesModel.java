package backend.service.reqResModel.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponseResizedImagesModel {

    private String imagePath;

    private int width;

}
