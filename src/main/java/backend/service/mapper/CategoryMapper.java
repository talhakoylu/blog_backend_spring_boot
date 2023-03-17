package backend.service.mapper;


import backend.core.utils.SlugHelper;
import backend.model.Category;
import backend.model.Image;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.serviceInterface.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public abstract class CategoryMapper {

    @Autowired
    private ImageService imageService;

    @Mapping(target = "image", expression = "java(toImage(createCategoryRequest.getCoverImageId()))")
    @Mapping(target = "slug", expression = "java(toSlug(createCategoryRequest.getSlug(), createCategoryRequest.getTitle()))")
    @Mapping(target = "isActive", source = "isActive", defaultExpression = "java(true)")
    public abstract Category createCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    public abstract CreateCategoryResponse categoryToCreateCategoryResponse(Category category);

    protected Image toImage(String id){
        if (id == null) return null;

        return this.imageService.findByIdForMapper(id);
    }

    protected String toSlug(String slug, String title){
        if(slug != null){
            return SlugHelper.toSlug(slug);
        }else {
            return SlugHelper.toSlug(title);
        }
    }

}
