package backend.service.mapper;

import backend.core.utils.fileUpload.FileModel;
import backend.model.Category;
import backend.model.Image;
import backend.model.OptimizedImage;
import backend.model.Post;
import backend.service.reqResModel.image.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    CreateImageResponse imageToCreateImageResponse(Image image);

    @Mapping(target = "imagePath", source = "fileModel.convertedPath")
    @Mapping(target = "originalImagePath", source = "fileModel.originalPath")
    @Mapping(target = "imageOriginalName", source = "fileModel.originalFileName")
    @Mapping(target = "imageUniqueName", source = "fileModel.uniqueFileName")
    @Mapping(target = "contentType", source = "fileModel.contentType")
    @Mapping(target = "imageSize", source = "fileModel.fileSize")
    @Mapping(target = "title", source = "createImageRequest.title")
    @Mapping(target = "altText", source = "createImageRequest.altText")
    @Mapping(target = "isActive", source = "createImageRequest.isActive", defaultExpression = "java(true)")
    Image fileModelAndCreateImageRequestToImage(FileModel fileModel, CreateImageRequest createImageRequest);

    SoftDeleteByIdImagePostModel postToSoftDeleteByIdImagePostModel(Post post);

    List<SoftDeleteByIdImagePostModel> postListToSoftDeleteByIdImagePostModelList(List<Post> posts);

    SoftDeleteByIdImageCategoryModel categoryToSoftDeleteByIdImageCategoryModel(Category category);
    List<SoftDeleteByIdImageCategoryModel> categoryToSoftDeleteByIdImageCategoryModel(List<Category> categories);

    SoftDeleteByIdImageResponse imageToSoftDeleteByIdImageResponse(Image image);

    //GetAllMappings

    @Mapping(target = "imageName", source = "uniqueName")
    GetAllImageListResponseResizedVersionsModel optimizedImageToGetAllImageListResponseResizedVersionsModel(OptimizedImage optimizedImage);

    List<GetAllImageListResponseResizedVersionsModel> optimizedImageListToGetAllImageListResponseResizedVersionsModelList(List<OptimizedImage> optimizedImages);

    @Mapping(target = "imageName", source = "imageUniqueName")
    @Mapping(target = "resizedImages", source = "optimizedImages")
    GetAllImageListResponse imageToGetAllImageListResponse(Image image);

    List<GetAllImageListResponse> imageListToGetAllImageListResponseList(List<Image> images);


}
