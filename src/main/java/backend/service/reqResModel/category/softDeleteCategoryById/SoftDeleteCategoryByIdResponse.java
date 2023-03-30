package backend.service.reqResModel.category.softDeleteCategoryById;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftDeleteCategoryByIdResponse {

    private String title;

    private String slug;

    private List<SoftDeleteCategoryByIdResponsePostModel> posts;

}
