package backend.core.utils.fileUpload;

import backend.core.utils.exceptions.FileUploadServiceException;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final Path mainRoot = Paths.get("src/main");

    private final Path fileRoot = Paths.get("uploads/files/");
    private final Path imageRoot = Paths.get("uploads/images/");

    private final Path removedImageRoot = Paths.get("uploads/removedImages/");
    private final Path removedFileRoot = Paths.get("uploads/removedFiles/");

    private FileUploadBusinessRules fileUploadBusinessRules;

    private final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Override
    public void init() {
        try {
            Files.createDirectories(this.mainRoot.resolve(this.fileRoot.toString()));
            Files.createDirectories(this.mainRoot.resolve(this.imageRoot.toString()));
            Files.createDirectories(this.mainRoot.resolve(this.removedImageRoot.toString()));
            Files.createDirectories(this.mainRoot.resolve(this.removedFileRoot.toString()));
        } catch (IOException e) {
            log.error("FileUploadServiceImplInit IOException", e);
            throw new FileUploadServiceException("Could not initialize folder for upload!");
        } catch (Exception e) {
            log.error("FileUploadServiceImplInit Exception", e);
            throw new FileUploadServiceException("Something went wrong while the folders are creating.");
        }
    }

    @Override
    public FileModel uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public FileModel uploadImage(MultipartFile file) {
        this.fileUploadBusinessRules.checkImageUploadContentType(file.getContentType());

        String uniqueFileName = new StringBuilder().append(UUID.randomUUID()).append(".")
                .append(FilenameUtils.getExtension(file.getOriginalFilename())).toString();

        Path path = this.imageRoot.resolve(uniqueFileName);

        try {
            Files.copy(file.getInputStream(), this.mainRoot.resolve(path.toString()));
        } catch (FileAlreadyExistsException e) {
            log.error("FileUploadServiceImplUploadImage FileAlreadyExistsException", e);
            throw new FileUploadServiceException("Image already exists with this name.");
        } catch (IOException e) {
            log.error("FileUploadServiceImplUploadImage IOException", e);
            throw new FileUploadServiceException("Something went wrong while the image uploading.");
        }

        FileModel result = new FileModel();
        result.setFileSize(file.getSize());
        result.setOriginalFileName(file.getOriginalFilename());
        result.setOriginalPath(path.toString());
        result.setConvertedPath(path.toString().replaceAll("\\\\", "/"));
        result.setUniqueFileName(uniqueFileName);
        result.setContentType(file.getContentType());

        return result;
    }

    @Override
    public boolean delete(String fileName) {
        try {
            Path path = this.imageRoot.resolve(fileName);
            return Files.deleteIfExists(this.mainRoot.resolve(path.toString()));
        } catch (IOException e) {
            throw new FileUploadServiceException("Something went wrong while deleting the file.");
        }
    }

    @Override
    public Resource getImageByImagePath(String imagePath) {
        try{
            File file = new File(this.mainRoot.resolve(this.imageRoot.resolve(imagePath)).toString());
            if (file.exists()) {
                return new UrlResource(file.toURI());
            } else {
                return null;
            }
        }catch (MalformedURLException e){
            log.error("FileUploadServiceGetImagebyImagePathMalformedURLException", e);
            throw new FileUploadServiceException("An error occured while getting the image.");
        }
    }
}
