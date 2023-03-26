package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftDeleteCategoryByIdResponse {

    private String title;

    private List<SoftDeleteCategoryByIdResponsePostModel> posts;

}
