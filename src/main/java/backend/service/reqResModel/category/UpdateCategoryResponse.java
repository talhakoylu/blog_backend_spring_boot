package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryResponse {

    private String title;

    private String description;

    private String slug;

    private String seoTitle;

    private String seoDescription;

    private String seoTags;

    private boolean isActive;

    private UpdateCategoryResponseImageModel coverImage;

}
