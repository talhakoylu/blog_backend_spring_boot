package backend.service.mapper;

import backend.core.utils.fileUpload.FileModel;
import backend.model.OptimizedImage;
import org.mapstruct.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
@Service
public abstract class OptimizedImageMapper {

    @Mapping(target = "uniqueName", source = "uniqueFileName")
    @Mapping(target = "imagePath", source = "convertedPath")
    @Mapping(target = "image.id", expression = "java(id)")
    public abstract OptimizedImage fileModelToOptimizedImage(FileModel fileModel, @Context UUID id);

    public abstract List<OptimizedImage> fileModelListToOptimizedImageList(List<FileModel> fileModelList, @Context UUID id);


}
