package backend.core.utils.fileUpload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    void init();

    FileModel uploadFile(MultipartFile file);

    FileModel uploadImage(MultipartFile file);

    Resource load(String fileName);

}
