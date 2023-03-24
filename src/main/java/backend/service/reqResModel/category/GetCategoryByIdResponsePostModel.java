package backend.service.reqResModel.category;

import backend.common.PostStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryByIdResponsePostModel {

    private String id;

    private String title;

    private String slug;

    private PostStatusEnum postStatus;

}
