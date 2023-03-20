package backend.service.reqResModel.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllImageListResponse {

    private String title;

    private String imageName;

    private String imagePath;

    private long imageSize;

    private String altText;

    private List<GetAllImageListResponseResizedVersionsModel> resizedImages;

}
