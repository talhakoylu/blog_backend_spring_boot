package backend.service.mapper;

import backend.model.Category;
import backend.model.Image;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.reqResModel.category.GetAllCategoryResponse;
import backend.service.reqResModel.category.GetByIdCategoryResponse;
import backend.service.serviceInterface.ImageService;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = ImageService.class)
public abstract class CategoryMapper {

    @Autowired
    protected ImageService imageService;

    @Mapping(target = "slug", expression = "java(createCategoryRequest.getTitle().toLowerCase())")
    @Mapping(target = "image", expression = "java(imageService.findByIdForMapper(createCategoryRequest.getCoverImageId()))")
    public abstract Category createCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    @Mapping(source = "category.image.imagePath", target = "imagePath")
    @Mapping(source = "category.image.altText", target = "imageAltText")
    public abstract CreateCategoryResponse categoryToCreateCategoryResponse(Category category);

    @Mapping(source = "category.image.imagePath", target = "imagePath")
    @Mapping(source = "category.image.altText", target = "imageAltText")
    public abstract GetByIdCategoryResponse categoryToGetByIdCategoryResponse(Category category);

    @Mapping(source = "category.image", target = "image", qualifiedByName = "mapImage")
    public abstract GetAllCategoryResponse categoryToGetAllCategoryResponse(Category category);

    public abstract List<GetAllCategoryResponse> categoryToGetAllCategoryListResponse(List<Category> category);

    @Named("mapImage")
    Map<String, String> mapImage(Image image){
        Map<String, String> toMapImage = new HashMap<>();
        
        if (image == null) return null;

        toMapImage.putIfAbsent("imagePath", image.getImagePath());
        toMapImage.putIfAbsent("imageAltText", image.getAltText());

        return toMapImage;

    }

}
