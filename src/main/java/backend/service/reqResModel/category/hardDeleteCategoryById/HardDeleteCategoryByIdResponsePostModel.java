package backend.service.reqResModel.category.hardDeleteCategoryById;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardDeleteCategoryByIdResponsePostModel {

    private String title;

    private String slug;

}
