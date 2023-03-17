package backend.service.mapper;

import backend.common.PostStatusEnum;
import backend.core.utils.SlugHelper;
import backend.core.utils.exceptions.MappingException;
import backend.model.Category;
import backend.model.Image;
import backend.model.Post;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.reqResModel.post.CreatePostResponseCategoryModel;
import backend.service.reqResModel.post.CreatePostResponseImageModel;
import backend.service.serviceInterface.CategoryService;
import backend.service.serviceInterface.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Mapper(componentModel = "spring", uses = {CategoryService.class, ImageService.class})
@Service
public abstract class PostMapper {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Mapping(target = "postStatus", source = "postStatus", qualifiedByName = "stringToStatus")
    @Mapping(target = "coverImage", expression = "java(toImage(createPostRequest.getCoverImageId()))")
    @Mapping(target = "category", expression = "java(toCategory(createPostRequest.getCategoryId()))")
    @Mapping(target = "slug", expression = "java(toSlug(createPostRequest.getSlug(), createPostRequest.getTitle()))")
    public abstract Post createPostRequestToPost(CreatePostRequest createPostRequest);

    public abstract CreatePostResponseCategoryModel categoryToCreatePostResponseCategoryModel(Category category);

    public abstract CreatePostResponseImageModel imageToCreatePostResponseImageModel(Image image);

    public abstract CreatePostResponse postToCreatePostResponse(Post post);

    @Named("stringToStatus")
    protected PostStatusEnum stringToStatus(String postStatus) {

        if(postStatus == null || postStatus.isEmpty() || postStatus.isBlank()){
            postStatus = "task";
        }

        String status = postStatus.toUpperCase(Locale.ENGLISH);

        return switch (status) {
            case "ACTIVE" -> PostStatusEnum.ACTIVE;
            case "TASK" -> PostStatusEnum.TASK;
            case "REMOVED" -> PostStatusEnum.REMOVED;
            default -> throw new MappingException("Post status does not have correct value.");
        };

    }

    protected Image toImage(String id){
        if (id == null) return null;

        return this.imageService.findByIdForMapper(id);
    }

    protected Category toCategory(String id){
        if (id == null) return null;

        return this.categoryService.findByIdForMapper(id);
    }

    protected String toSlug(String slug, String title){
        if(slug != null){
            return SlugHelper.toSlug(slug);
        }else {
            return SlugHelper.toSlug(title);
        }
    }



}
