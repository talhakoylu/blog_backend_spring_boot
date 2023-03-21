package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryDetailsBySlugResponse {

    private String title;

    private String description;

    private String slug;

    private String seoTitle;

    private String seoDescription;

    private String seoTags;

    private GetCategoryDetailsBySlugResponseImageModel coverImage;

    private List<GetCategoryDetailsBySlugResponsePostModel> posts;

}
