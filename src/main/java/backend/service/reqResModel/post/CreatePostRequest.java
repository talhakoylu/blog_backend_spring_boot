package backend.service.reqResModel.post;

import backend.common.PostStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    @NotNull
    @NotBlank
    @Size(min = 15, max = 150)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 100)
    private String content;

    private String slug;

    private String postStatus = PostStatusEnum.TASK.toString();

    private String coverImageId;

    private String categoryId;

    @NotBlank
    @NotNull
    @Size(min = 15, max = 150)
    private String seoTitle;

    @NotBlank
    @NotNull
    @Size(min = 15, max = 250)
    private String seoDescription;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    private String seoTags;

}
