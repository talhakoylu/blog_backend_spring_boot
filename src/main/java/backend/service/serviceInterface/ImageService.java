package backend.service.serviceInterface;

import backend.model.Image;
import backend.service.reqResModel.image.AddImageRequest;
import backend.service.reqResModel.image.AddImageResponse;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;


public interface ImageService {

    AddImageResponse add(AddImageRequest addImageRequest);

    Image findByIdForMapper(String id);

    CreateImageResponse uploadImage(CreateImageRequest createImageRequest);


}
