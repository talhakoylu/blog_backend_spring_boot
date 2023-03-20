package backend.service.serviceInterface;

import backend.core.utils.fileUpload.FileModel;
import backend.model.OptimizedImage;

import java.util.List;
import java.util.UUID;

public interface OptimizedImageService {

    void saveAll(List<FileModel> fileModels, UUID imageId);

}
