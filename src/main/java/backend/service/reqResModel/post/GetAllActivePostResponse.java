package backend.service.reqResModel.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllActivePostResponse {

    private String title;

    private String content;

    private String slug;

    private GetAllActivePostResponseCategoryModel category;

    private GetAllActivePostResponseImageModel coverImage;

    private String seoTitle;

    private String seoDescription;

    private String seoTags;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
