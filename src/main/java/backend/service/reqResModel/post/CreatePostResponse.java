package backend.service.reqResModel.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponse {

    private String title;

    private String content;

    private String slug;

    private CreatePostResponseCategoryModel category;

    private CreatePostResponseImageModel coverImage;

    private String seoTitle;

    private String seoDescription;

    private String seoTags;

}
