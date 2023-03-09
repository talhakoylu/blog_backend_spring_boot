package backend.service.reqResModel.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddImageRequest {

    private String imagePath;

    private String altText;

    private Boolean isActive = true;
}
