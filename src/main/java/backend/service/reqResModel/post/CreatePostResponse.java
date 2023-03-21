package backend.service.reqResModel.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponse {

    private String title;

    private String content;

    private String slug;

    private String seoTitle;

    private String seoDescription;

    private String seoTags;

    private CreatePostResponseImageModel coverImage;

    private CreatePostResponseCategoryModel category;

}
