package backend.service.mapper;

import backend.common.PostStatusEnum;
import backend.core.utils.SlugHelper;
import backend.core.utils.exceptions.MappingException;
import backend.model.Category;
import backend.model.Image;
import backend.model.Post;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostRequestCategoryModel;
import backend.service.reqResModel.post.CreatePostRequestImageModel;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.serviceInterface.CategoryService;
import backend.service.serviceInterface.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {CategoryService.class, ImageService.class})
@Service
public abstract class PostMapper {


    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUUID")
    protected abstract Image createPostRequestImageModelToImage(CreatePostRequestImageModel createPostRequestImageModel);

    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUUID")
    protected abstract Category createPostRequestCategoryModelToCategory(CreatePostRequestCategoryModel createPostRequestCategoryModel);

    @Mapping(target = "postStatus", source = "postStatus", qualifiedByName = "stringToStatus")
    @Mapping(target = "slug", expression = "java(toSlug(createPostRequest.getSlug(), createPostRequest.getTitle()))")
    public abstract Post createPostRequestToPost(CreatePostRequest createPostRequest);

    public abstract CreatePostResponse postToCreatePostResponse(Post post);

    //region Helper Methods
    @Named("stringToUUID")
    protected UUID stringToUUID(String id){
        try{
            return UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new MappingException("Unacceptable id value.");
        }
    }

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

    protected String toSlug(String slug, String title){
        if(slug != null){
            return SlugHelper.toSlug(slug);
        }else {
            return SlugHelper.toSlug(title);
        }
    }

    //endregion

}
