package backend.core.utils.fileUpload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FileUploadService {

    void init();

    FileModel uploadFile(MultipartFile file);

    FileModel uploadImage(MultipartFile file);

    boolean delete(String fileName);

    Resource getImageByImagePath(String imagePath);

    CompletableFuture<List<FileModel>> asyncImageOptimizer(String fullFileName, String onlyUniqueName, String onlyExtension, String contentType);

}
