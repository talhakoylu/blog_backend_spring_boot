package backend.api.controller;

import backend.core.apiResponse.ApiResponse;
import backend.core.validations.UUIDValidation.UUIDValidation;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.reqResModel.image.GetAllImageListResponse;
import backend.service.reqResModel.image.SoftDeleteByIdImageResponse;
import backend.service.serviceInterface.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
@Validated
public class ImageController {

    private ImageService imageService;


    @PostMapping("upload-image")
    @Operation(summary = "Upload Image", description = "An endpoint to upload image and save result to db.")
    public ResponseEntity<ApiResponse<CreateImageResponse>> uploadImage(@ModelAttribute @Valid CreateImageRequest createImageRequest){
        return this.imageService.uploadImage(createImageRequest);
    }

    @DeleteMapping("delete-by-id")
    @Operation(summary = "Soft Delete to Image", description = "This request will change the status of image to inactive, but not remove the image from our servers.")
    public ResponseEntity<ApiResponse<SoftDeleteByIdImageResponse>> softDeleteById(@RequestParam("id") @Valid @UUIDValidation String id ){
        return this.imageService.softDeleteById(id);
    }

    @GetMapping("get-all")
    public ResponseEntity<ApiResponse<List<GetAllImageListResponse>>> getAllImages(){
        return this.imageService.getAllImages();
    }

}
