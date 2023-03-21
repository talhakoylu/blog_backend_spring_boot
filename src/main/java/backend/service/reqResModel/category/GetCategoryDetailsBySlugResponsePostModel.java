package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryDetailsBySlugResponsePostModel {

    private String title;

    private String content;

    private String slug;

    private GetCategoryDetailsBySlugResponseImageModel coverImage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private GetCategoryDetailsBySlugResponseUserModel createdBy;

    private GetCategoryDetailsBySlugResponseUserModel updatedBy;
}
