package backend.service.serviceInterface;

import backend.model.Image;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.reqResModel.image.SoftDeleteByIdImageResponse;


public interface ImageService {

    Image findByIdForMapper(String id);

    CreateImageResponse uploadImage(CreateImageRequest createImageRequest);

    SoftDeleteByIdImageResponse softDeleteById(String id);


}
