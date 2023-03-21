package backend.service.mapper;


import backend.core.utils.SlugHelper;
import backend.model.*;
import backend.service.reqResModel.category.*;
import backend.service.serviceInterface.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public abstract class CategoryMapper {

    @Autowired
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

    //region Helper Methods
    protected Image toImage(String id) {
        if (id == null) return null;

        return this.imageService.findByIdForMapper(id);
    }

    protected String toSlug(String slug, String title) {
        if (slug != null) {
            return SlugHelper.toSlug(slug);
        } else {
            return SlugHelper.toSlug(title);
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
