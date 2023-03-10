package backend.service.reqResModel.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftDeleteByIdImagePostModel {

    private String title;

    private String slug;

}
