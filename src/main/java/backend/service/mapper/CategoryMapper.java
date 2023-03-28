package backend.service.mapper;


import backend.core.utils.SlugHelper;
import backend.core.utils.exceptions.MappingException;
import backend.model.*;
import backend.service.reqResModel.category.*;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryRequest;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryRequestImageModel;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryResponse;
import backend.service.serviceInterface.ImageService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
@Service
public abstract class CategoryMapper {

    @Autowired
    @Lazy
    private ImageService imageService;


    //region CreateCategory mappings
    @Mapping(target = "image", expression = "java(toImage(createCategoryRequest.getCoverImageId()))")
    @Mapping(target = "slug", expression = "java(toSlug(createCategoryRequest.getSlug(), createCategoryRequest.getTitle()))")
    @Mapping(target = "isActive", source = "isActive", defaultExpression = "java(true)")
    public abstract Category createCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    public abstract CreateCategoryResponse categoryToCreateCategoryResponse(Category category);
    //endregion

    //region GetCategoryBySlug Mappings
    protected abstract GetCategoryDetailsBySlugResponseUserModel userToGetCategoryDetailsBySlugResponseUserModel(User user);

    @Mapping(target = "content", expression = "java(toShortString(post.getContent(), 150))")
    protected abstract GetCategoryDetailsBySlugResponsePostModel postToGetCategoryDetailsBySlugResponsePostModel(Post post);

    protected abstract List<GetCategoryDetailsBySlugResponsePostModel> postsListToGetCategoryDetailsBySlugResponsePostModelList(List<Post> post);

    protected abstract GetCategoryDetailsBySlugResponseOptimizedImageModel optimizedImageToGetCategoryDetailsBySlugResponseOptimizedImageModel(OptimizedImage optimizedImage);

    protected abstract List<GetCategoryDetailsBySlugResponseOptimizedImageModel> optimizedImageListToGetCategoryDetailsBySlugResponseOptimizedImageModelList(List<OptimizedImage> optimizedImages);

    @Mapping(target = "resizedImages", source = "optimizedImages")
    protected abstract GetCategoryDetailsBySlugResponseImageModel imageToGetCategoryDetailsBySlugResponseImageModel(Image image);

    @Mapping(target = "coverImage", source = "image")
    public abstract GetCategoryDetailsBySlugResponse categoryToGetCategoryDetailsBySlugResponse(Category category);
    //endregion

    //region UpdateCategory mappings

    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUUID")
    protected abstract Image updateCategoryRequestImageModelToImage(UpdateCategoryRequestImageModel updateCategoryRequestImageModel);

    @Mapping(target = "image", source = "coverImage", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "slug", expression = "java(toSlug(updateCategoryRequest.getSlug(), updateCategoryRequest.getTitle(), category.getSlug()))")
    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(updateCategoryRequest.getId()))")
    @Mapping(target = "isActive", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Category updateCategoryRequestAndCategoryToCategory(@MappingTarget Category category, UpdateCategoryRequest updateCategoryRequest);

    public abstract UpdateCategoryResponse categoryToUpdateCategoryResponse(Category category);

    //endregion

    //region GetCategoryById Mappings

    protected abstract GetCategoryByIdResponseOptimizedImageModel optimizedImageToGetCategoryByIdResponseOptimizedImageModel(OptimizedImage optimizedImage);

    protected abstract List<GetCategoryByIdResponseOptimizedImageModel> optimizedImageListToGetCategoryByIdResponseOptimizedImageModel(List<OptimizedImage> optimizedImages);

    @Mapping(target = "resizedImages", source = "optimizedImages")
    protected abstract GetCategoryByIdResponseImageModel imageToGetCategoryByIdResponseImageModel(Image image);

    protected abstract GetCategoryByIdResponsePostModel postToGetCategoryByIdResponsePostModel(Post post);

    protected abstract List<GetCategoryByIdResponsePostModel> postListToGetCategoryByIdResponsePostModelList(List<Post> post);

    @Mapping(target = "coverImage", source = "image")
    public abstract GetCategoryByIdResponse categoryToGetCategoryByIdResponse(Category category);

    //endregion

    //region SoftDeleteCategoryById

    protected abstract SoftDeleteCategoryByIdResponsePostModel postToSoftDeleteCategoryByIdResponsePostModel(Post post);

    protected abstract List<SoftDeleteCategoryByIdResponsePostModel> postListToSoftDeleteCategoryByIdResponsePostModelList(List<Post> posts);

    public abstract SoftDeleteCategoryByIdResponse categoryToSoftDeleteIdResponse(Category category);

    //endregion

    //region Helper Methods
    @Named("stringToUUID")
    protected UUID stringToUUID(String id){
        try{
            return UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new MappingException("Unacceptable id value.");
        }
    }

    protected Image toImage(String id) {
        if (id == null) return null;

        return this.imageService.findByIdForMapper(id);
    }

    /**
     * To image method for update mapper. This method gets image id and image instance.
     * If imageId is null, method will return existing image instance. Also image instance is null
     * will return null.
     * @param id image id
     * @param image existing image instance from db.
     * @return Image or null
     */
    protected Image toImage(String id, Image image) {
        if (id == null) return null;
        else if(image != null && id.equals(image.getId().toString())) return image;
        else return this.imageService.findByIdForMapper(id);
    }

    protected String toSlug(String slug, String title) {
        if (slug != null) {
            return SlugHelper.toSlug(slug);
        } else {
            return SlugHelper.toSlug(title);
        }
    }

    /**
     * String To slug method for update methods
     * @param requestSlug slug value from request
     * @param requestTitle title value from request. If slug value not exists create new slug with this param.
     * @param existsRecordSlug if title and slug request values are null, will return existing slug value.
     * @return String slug
     */
    protected String toSlug(String requestSlug, String requestTitle, String existsRecordSlug) {
        if(requestSlug != null){
            return SlugHelper.toSlug(requestSlug);
        }else if(requestTitle != null){
            return SlugHelper.toSlug(requestTitle);
        }else{
            return existsRecordSlug;
        }
    }

    protected String toShortString(String input, int length) {
        if (input.length() > length) {
            return input.substring(0, length);
        } else {
            return input;
        }
    }

    //endregion

}
