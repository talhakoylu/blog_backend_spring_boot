package backend.service.mapper;

import backend.common.PostStatusEnum;
import backend.core.utils.exceptions.MappingException;
import backend.model.Category;
import backend.model.Image;
import backend.model.Post;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.reqResModel.post.GetAllActivePostResponse;
import backend.service.serviceInterface.CategoryService;
import backend.service.serviceInterface.ImageService;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = "spring")
@Service
public abstract class PostMapper {

    @Autowired
    protected ImageService imageService;

    @Autowired
    protected CategoryService categoryService;

    @Mapping(source = "postStatus", target = "postStatus", qualifiedByName = "stringToStatus")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryIdToCategory")
    @Mapping(source = "coverImageId", target = "coverImage", qualifiedByName = "coverImageIdToCoverImage")
    @Mapping(source = "slug", target = "slug", defaultExpression = "java(backend.core.utils.SlugHelper.toSlug(createPostRequest.getTitle()))")
    public abstract Post CreatePostRequestToPost(CreatePostRequest createPostRequest) throws MappingException;

    @Mapping(source = "post.category.title", target = "category.title")
    @Mapping(source = "post.category.slug", target = "category.slug")
    @Mapping(source = "post.coverImage.imagePath", target = "coverImage.imagePath")
    @Mapping(source = "post.coverImage.altText", target = "coverImage.altText")
    public abstract CreatePostResponse postToCreatePostResponse(Post post);

    @Mapping(source = "post.category.title", target = "category.title")
    @Mapping(source = "post.category.slug", target = "category.slug")
    @Mapping(source = "post.coverImage.imagePath", target = "coverImage.imagePath")
    @Mapping(source = "post.coverImage.altText", target = "coverImage.altText")
    public abstract GetAllActivePostResponse postToGetAllPostActiveResponse(Post post);

    public abstract List<GetAllActivePostResponse> getAllActivePostResponseListMapper(List<Post> posts);

    @Named("coverImageIdToCoverImage")
    protected Image coverImageIdToCoverImage(String id){
        if(id == null) return null;

        return this.imageService.findByIdForMapper(id);
    }

    @Named("categoryIdToCategory")
    protected Category categoryIdToCategory(String id){
        if(id == null) return null;

        return this.categoryService.findByIdForMapper(id);
    }

    @Named("stringToStatus")
    protected PostStatusEnum stringToStatus(String postStatus) {
        String status = postStatus.toUpperCase(Locale.ENGLISH);

        return switch (status) {
            case "ACTIVE" -> PostStatusEnum.ACTIVE;
            case "TASK" -> PostStatusEnum.TASK;
            case "REMOVED" -> PostStatusEnum.REMOVED;
            default -> throw new MappingException("Post status does not have correct value.");
        };

    }



}
