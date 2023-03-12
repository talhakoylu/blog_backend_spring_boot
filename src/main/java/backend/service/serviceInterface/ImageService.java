package backend.service.serviceInterface;

import backend.core.apiResponse.ApiResponse;
import backend.model.Image;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.reqResModel.image.SoftDeleteByIdImageResponse;
import org.springframework.http.ResponseEntity;


public interface ImageService {

    Image findByIdForMapper(String id);

    ResponseEntity<ApiResponse<CreateImageResponse>> uploadImage(CreateImageRequest createImageRequest);

    ResponseEntity<ApiResponse<SoftDeleteByIdImageResponse>> softDeleteById(String id);


}
