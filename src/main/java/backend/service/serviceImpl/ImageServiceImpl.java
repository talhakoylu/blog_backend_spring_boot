package backend.service.serviceImpl;

import backend.core.apiResponse.ApiResponse;
import backend.core.apiResponse.ResponseHelper;
import backend.core.utils.exceptions.NotFoundException;
import backend.core.utils.exceptions.ServiceLayerException;
import backend.core.utils.fileUpload.FileModel;
import backend.core.utils.fileUpload.FileUploadService;
import backend.model.Image;
import backend.repository.ImageRepository;
import backend.service.mapper.ImageMapper;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.reqResModel.image.GetAllImageListResponse;
import backend.service.reqResModel.image.SoftDeleteByIdImageResponse;
import backend.service.serviceInterface.ImageService;
import backend.service.serviceInterface.OptimizedImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private ImageMapper imageMapper;

    private OptimizedImageService optimizedImageService;

    private ImageRepository imageRepository;

    private FileUploadService fileUploadService;

    private ResponseHelper responseHelper;

    private AsyncTaskExecutor asyncTaskExecutor;

    @Override
    public Image findByIdForMapper(String id) {
        return this.imageRepository.findByIdAndIsActiveTrue(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("No image with this parameter was found. Image could not be active."));
    }

    @Override
    public ResponseEntity<ApiResponse<CreateImageResponse>> uploadImage(CreateImageRequest createImageRequest) {

        FileModel imageFile = this.fileUploadService.uploadImage(createImageRequest.getImage());
        Image toImage = this.imageMapper.fileModelAndCreateImageRequestToImage(imageFile, createImageRequest);
        Image result = this.imageRepository.save(toImage);

        asyncTaskExecutor.execute(() -> {
            List<FileModel> fileModels;

            try {
                fileModels = this.fileUploadService.asyncImageOptimizer(imageFile.getUniqueFileName(), imageFile.getOnlyUniqueName(), imageFile.getOnlyExtension(), imageFile.getContentType()).get();

                if (fileModels != null && fileModels.size() > 0) {
                    result.setId(result.getId());

                    fileModels.forEach(item -> {
                                if (item.getWidth() == 0) {
                                    result.setImageSize(item.getFileSize());
                                    this.imageRepository.save(result);
                                }
                            }
                    );

                    this.optimizedImageService.saveAll(fileModels, result.getId());
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new ServiceLayerException("Something went wrong while the optimized images saving.");
            }
        });

        return this.responseHelper.buildResponse(HttpStatus.CREATED.value(), "Image uploaded.",
                this.imageMapper.imageToCreateImageResponse(result));
    }

    @Override
    public ResponseEntity<ApiResponse<SoftDeleteByIdImageResponse>> softDeleteById(String id) {

        Image findByIdResult = this.imageRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No image found."));

        findByIdResult.setIsActive(false);

        Image saveResult = this.imageRepository.save(findByIdResult);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Image removed successfully.",
                this.imageMapper.imageToSoftDeleteByIdImageResponse(saveResult));
    }

    @Override
    public ResponseEntity<Object> getImageByPath(String imageName) {

        Image result = this.imageRepository.findImageByUniqueNameInImagesOrOptimizedImages(imageName)
                .orElseThrow(() -> new NotFoundException("Image not found."));

        if (result.getIsActive()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.asMediaType(MimeType.valueOf(result.getContentType())));

            Resource resource = this.fileUploadService.getImageByImagePath(imageName);

            if (resource == null || !resource.exists()) {
                throw new NotFoundException("Image not found.");
            }

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(resource);
        } else {
            throw new NotFoundException("Image file not found or inactive.");
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<GetAllImageListResponse>>> getAllImages() {

        List<GetAllImageListResponse> images = this.imageMapper.imageListToGetAllImageListResponseList(this.imageRepository.findAll());

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Images listed.", images);
    }


}
