package backend.core.utils.fileUpload;

import backend.core.utils.exceptions.FileUploadServiceException;
import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private ImageOptimizer imageOptimizer;

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

        String uniqueName = UUID.randomUUID().toString();
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());

        Path path = this.imageRoot.resolve(uniqueName + extension);

        try {
            Files.copy(file.getInputStream(), this.mainRoot.resolve(path.toString()));
//            Files.write(this.mainRoot.resolve(path.toString()), this.imageOptimizer.compressor(file, 0.4f));
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
        result.setUniqueFileName(uniqueName + extension);
        result.setContentType(file.getContentType());
        result.setOnlyExtension(extension);
        result.setOnlyUniqueName(uniqueName);

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
        try {
            File file = new File(this.mainRoot.resolve(this.imageRoot.resolve(imagePath)).toString());
            if (file.exists()) {
                return new UrlResource(file.toURI());
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            log.error("FileUploadServiceGetImagebyImagePathMalformedURLException", e);
            throw new FileUploadServiceException("An error occured while getting the image.");
        }
    }

    @Override
    @Async
    public CompletableFuture<List<FileModel>> asyncImageOptimizer(String fullFileName, String onlyUniqueName, String onlyExtension, String contentType) {
        List<FileModel> fileModels = new ArrayList<>();

        String imageOutputFormat = null;

        if (contentType.equals("image/jpeg")) {
            imageOutputFormat = "jpeg";
        } else if (contentType.equals("image/png")) {
            imageOutputFormat = "png";
        } else {
            return null;
        }

        byte[] optimizedImageByteArray = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(this.mainRoot.resolve(this.imageRoot.resolve(fullFileName).toString())
                    .toFile());

            if (bufferedImage.getWidth() >= 350) {
                optimizedImageByteArray = this.optimizeImage(bufferedImage, 350, (350 * bufferedImage.getHeight()) / bufferedImage.getWidth(), 0.4f, imageOutputFormat);
                Files.write(this.mainRoot.resolve(this.imageRoot.resolve(onlyUniqueName + "_350" + onlyExtension).toString()), optimizedImageByteArray);
                fileModels.add(this.getFileModel(onlyUniqueName, onlyExtension, "_350", optimizedImageByteArray.length, 350));
            }

            if (bufferedImage.getWidth() >= 650) {
                optimizedImageByteArray = this.optimizeImage(bufferedImage, 650, (650 * bufferedImage.getHeight()) / bufferedImage.getWidth(), 0.4f, imageOutputFormat);
                Files.write(this.mainRoot.resolve(this.imageRoot.resolve(onlyUniqueName + "_650" + onlyExtension).toString()), optimizedImageByteArray);
                fileModels.add(this.getFileModel(onlyUniqueName, onlyExtension, "_650", optimizedImageByteArray.length, 650));
            }

            if (bufferedImage.getWidth() >= 1050) {
                optimizedImageByteArray = this.optimizeImage(bufferedImage, 1050, (1050 * bufferedImage.getHeight()) / bufferedImage.getWidth(), 0.4f, imageOutputFormat);
                Files.write(this.mainRoot.resolve(this.imageRoot.resolve(onlyUniqueName + "_1050" + onlyExtension).toString()), optimizedImageByteArray);
                fileModels.add(this.getFileModel(onlyUniqueName, onlyExtension, "_1050", optimizedImageByteArray.length, 1050));
            }

            optimizedImageByteArray = this.optimizeImage(bufferedImage, bufferedImage.getWidth(), bufferedImage.getHeight(), 0.4f, imageOutputFormat);
            Files.write(this.mainRoot.resolve(this.imageRoot.resolve(onlyUniqueName + "" + onlyExtension).toString()), optimizedImageByteArray, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            fileModels.add(this.getFileModel(onlyUniqueName, onlyExtension, "", optimizedImageByteArray.length, 0));


        } catch (IOException e) {
            log.error("FileUploadServiceIOExceptionAsyncImageOptimizer", e);
            throw new FileUploadServiceException("Something went wrong while file uploading.");
        }
        return CompletableFuture.completedFuture(fileModels);
    }

    private FileModel getFileModel(String onlyUniqueName, String onlyExtension, String postFix, long fileSize, int width) {
        FileModel optimizedImageModel = new FileModel();
        optimizedImageModel.setFileSize(fileSize);
        optimizedImageModel.setUniqueFileName(onlyUniqueName + postFix + onlyExtension);
        optimizedImageModel.setOriginalPath(this.imageRoot.resolve(onlyUniqueName + postFix + onlyExtension).toString());
        optimizedImageModel.setConvertedPath(this.imageRoot.resolve(onlyUniqueName + postFix + onlyExtension).toString().replaceAll("\\\\", "/"));
        optimizedImageModel.setWidth(width);
        return optimizedImageModel;
    }

    private byte[] optimizeImage(BufferedImage bufferedImage, int width, int height, float quality, String imageOutputFormat) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(bufferedImage).size(width, height)
                    .keepAspectRatio(true)
                    .outputQuality(quality)
                    .outputFormat(imageOutputFormat)
                    .toOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            log.error("FileUploadServiceOptimizeImagePrivateIOException", e);
            throw new FileUploadServiceException("Something went wrong while file optimizing.");
        }

        return byteArrayOutputStream.toByteArray();
    }
}
