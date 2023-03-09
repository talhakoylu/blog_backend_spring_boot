package backend.service.serviceImpl;

import backend.core.utils.exceptions.MappingException;
import backend.core.utils.exceptions.NotFoundException;
import backend.core.utils.fileUpload.FileModel;
import backend.core.utils.fileUpload.FileUploadService;
import backend.model.Image;
import backend.repository.ImageRepository;
import backend.service.businessRules.ImageBusinessRules;
import backend.service.mapper.ImageMapper;
import backend.service.reqResModel.image.AddImageRequest;
import backend.service.reqResModel.image.AddImageResponse;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.serviceInterface.ImageService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final Path root = Paths.get("uploads/images/");

    private ImageMapper imageMapper;

    private ImageRepository imageRepository;

    private ImageBusinessRules imageBusinessRules;

    private FileUploadService fileUploadService;

    @Override
    public AddImageResponse add(AddImageRequest addImageRequest) {

        Image image = this.imageMapper.addImageRequestToImage(addImageRequest);

        Image result = this.imageRepository.save(image);

        return this.imageMapper.imageToAddImageResponse(result);
    }

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

}
