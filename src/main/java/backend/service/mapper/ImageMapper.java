package backend.service.mapper;

import backend.core.utils.fileUpload.FileModel;
import backend.model.Image;
import backend.model.Post;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.reqResModel.image.SoftDeleteByIdImagePostModel;
import backend.service.reqResModel.image.SoftDeleteByIdImageResponse;
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
    @Mapping(target = "imageSize", source = "fileModel.fileSize")
    @Mapping(target = "title", source = "createImageRequest.title")
    @Mapping(target = "altText", source = "createImageRequest.altText")
    @Mapping(target = "isActive", source = "createImageRequest.isActive", defaultExpression = "java(true)")
    Image fileModelAndCreateImageRequestToImage(FileModel fileModel, CreateImageRequest createImageRequest);

    SoftDeleteByIdImagePostModel postToSoftDeleteByIdImagePostModel(Post post);

    List<SoftDeleteByIdImagePostModel> postListToSoftDeleteByIdImagePostModelList(List<Post> posts);

    SoftDeleteByIdImageResponse imageToSoftDeleteByIdImageResponse(Image image);


}
