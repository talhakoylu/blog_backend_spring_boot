package backend.service.reqResModel.category.softDeleteCategoryById;

import backend.common.PostStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SoftDeleteCategoryByIdResponsePostModel {

    private UUID id;

    private String title;

    private String slug;

    private PostStatusEnum postStatus;

}
