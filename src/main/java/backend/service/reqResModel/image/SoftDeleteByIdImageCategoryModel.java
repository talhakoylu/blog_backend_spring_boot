package backend.service.reqResModel.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoftDeleteByIdImageCategoryModel {

    private String title;

    private String slug;

}
