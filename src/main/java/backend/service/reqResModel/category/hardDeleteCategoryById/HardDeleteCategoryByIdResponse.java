package backend.service.reqResModel.category.hardDeleteCategoryById;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardDeleteCategoryByIdResponse {

    private String title;

    private List<HardDeleteCategoryByIdResponsePostModel> posts;

}
