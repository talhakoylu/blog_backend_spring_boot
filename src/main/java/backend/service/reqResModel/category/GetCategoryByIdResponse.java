package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryByIdResponse {

    private String title;

    private String description;

    private String slug;

    private Boolean isActive;

    private GetCategoryByIdResponseImageModel coverImage;

    private List<GetCategoryByIdResponsePostModel> posts;

}
