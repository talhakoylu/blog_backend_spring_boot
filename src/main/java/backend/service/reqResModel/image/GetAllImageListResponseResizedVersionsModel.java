package backend.service.reqResModel.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllImageListResponseResizedVersionsModel {

    private String imageName;

    private String imagePath;

    private int width;

    private long fileSize;

}
