package backend.service.reqResModel.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftDeleteByIdImageResponse {

    private String title;

    private String imagePath;

    private List<SoftDeleteByIdImagePostModel> posts;

}
