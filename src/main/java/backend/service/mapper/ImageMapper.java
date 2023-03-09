package backend.service.mapper;

import backend.core.utils.fileUpload.FileModel;
import backend.model.Image;
import backend.service.reqResModel.image.AddImageRequest;
import backend.service.reqResModel.image.AddImageResponse;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image addImageRequestToImage(AddImageRequest addImageRequest);

    AddImageResponse imageToAddImageResponse(Image image);

    CreateImageResponse imageToCreateImageResponse(Image image);

//    @Mapping(target = "imagePath", expression = "java(imagePath)")
//    @Mapping(source = "active", target = "active", defaultValue = "true")
//    @Mapping(source = "altText", target = "altText")
//    Image createImageRequestToImage(CreateImageRequest createImageRequest, @Context String imagePath);

    @Mapping(target = "imagePath", source = "fileModel.convertedPath")
    @Mapping(target = "originalImagePath", source = "fileModel.originalPath")
    @Mapping(target = "imageOriginalName", source = "fileModel.originalFileName")
    @Mapping(target = "imageUniqueName", source = "fileModel.uniqueFileName")
    @Mapping(target = "imageSize", source = "fileModel.fileSize")
    @Mapping(target = "title", source = "createImageRequest.title")
    @Mapping(target = "altText", source = "createImageRequest.altText")
    @Mapping(target = "isActive", source = "createImageRequest.isActive", defaultExpression = "java(true)")
    Image fileModelAndCreateImageRequestToImage(FileModel fileModel, CreateImageRequest createImageRequest);

}
