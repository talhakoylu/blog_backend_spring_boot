package backend.service.mapper;


import backend.core.utils.SlugHelper;
import backend.model.Category;
import backend.model.Image;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {


    @Mapping(target = "image", expression = "java(toImage(createCategoryRequest.getCoverImageId()))")
    @Mapping(target = "slug", expression = "java(toSlug(createCategoryRequest.getSlug(), createCategoryRequest.getTitle()))")
    Category createCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    CreateCategoryResponse categoryToCreateCategoryResponse(Category category);

    default Image toImage(String id){
        if (id == null) return null;

        Image image = new Image();
        image.setId(UUID.fromString(id));

        return image;
    }

    default String toSlug(String slug, String title){
        if(slug != null){
            return SlugHelper.toSlug(slug);
        }else {
            return SlugHelper.toSlug(title);
        }
    }

}
