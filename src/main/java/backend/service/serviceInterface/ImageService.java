package backend.service.serviceInterface;

import backend.core.apiResponse.ApiResponse;
import backend.model.Image;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.reqResModel.image.GetAllImageListResponse;
import backend.service.reqResModel.image.SoftDeleteByIdImageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ImageService {

    Image findByIdForMapper(String id);

    ResponseEntity<ApiResponse<CreateImageResponse>> uploadImage(CreateImageRequest createImageRequest);

    ResponseEntity<ApiResponse<SoftDeleteByIdImageResponse>> softDeleteById(String id);

    ResponseEntity<Object> getImageByPath(String imagePath);

    ResponseEntity<ApiResponse<List<GetAllImageListResponse>>> getAllImages();

}
