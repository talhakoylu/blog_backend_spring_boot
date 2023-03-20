package backend.service.serviceImpl;

import backend.core.utils.fileUpload.FileModel;
import backend.model.OptimizedImage;
import backend.repository.OptimizedImageRepository;
import backend.service.mapper.OptimizedImageMapper;
import backend.service.serviceInterface.OptimizedImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OptimizedImageServiceImpl implements OptimizedImageService {

    private OptimizedImageRepository optimizedImageRepository;

    private OptimizedImageMapper mapper;

    @Override
    public void saveAll(List<FileModel> fileModels, UUID imageId) {
        List<OptimizedImage> optimizedImages = this.mapper.fileModelListToOptimizedImageList(fileModels, imageId)
                .stream().filter(item -> item.getWidth() != 0).toList();
        this.optimizedImageRepository.saveAll(optimizedImages);
    }
}
