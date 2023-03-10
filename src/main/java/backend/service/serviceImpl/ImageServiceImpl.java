package backend.service.serviceImpl;

import backend.common.PostStatusEnum;
import backend.core.utils.exceptions.NotFoundException;
import backend.core.utils.fileUpload.FileModel;
import backend.core.utils.fileUpload.FileUploadService;
import backend.model.Image;
import backend.repository.ImageRepository;
import backend.service.businessRules.ImageBusinessRules;
import backend.service.mapper.ImageMapper;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.reqResModel.image.SoftDeleteByIdImageResponse;
import backend.service.serviceInterface.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private ImageMapper imageMapper;

    private ImageRepository imageRepository;

    private ImageBusinessRules imageBusinessRules;

    private FileUploadService fileUploadService;

    @Override
    public Image findByIdForMapper(String id) {
        return this.imageRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("No image with this parameter was found."));
    }

    @Override
    public CreateImageResponse uploadImage(CreateImageRequest createImageRequest) {

        FileModel imageFile = this.fileUploadService.uploadImage(createImageRequest.getImage());
        Image toImage = this.imageMapper.fileModelAndCreateImageRequestToImage(imageFile, createImageRequest);
        Image result = this.imageRepository.save(toImage);

        return this.imageMapper.imageToCreateImageResponse(result);
    }

    @Override
    public SoftDeleteByIdImageResponse softDeleteById(String id) {
        Image findByIdResult = this.imageRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No image found."));

        findByIdResult.setIsActive(false);
        findByIdResult.getPosts().forEach(post -> post.setPostStatus(PostStatusEnum.TASK));

        Image saveResult = this.imageRepository.save(findByIdResult);

        return this.imageMapper.imageToSoftDeleteByIdImageResponse(saveResult);
    }


}
