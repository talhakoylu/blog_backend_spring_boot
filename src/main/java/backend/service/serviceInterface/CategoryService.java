package backend.service.serviceInterface;

import backend.core.apiResponse.ApiResponse;
import backend.model.Category;
import backend.service.reqResModel.category.*;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<ApiResponse<CreateCategoryResponse>> addCategory(CreateCategoryRequest createCategoryRequest);

    Category findByIdForMapper(String id);

    ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlug(String slug);

    ResponseEntity<ApiResponse<UpdateCategoryResponse>> updateCategory(UpdateCategoryRequest updateCategoryRequest);

    ResponseEntity<ApiResponse<GetCategoryByIdResponse>> getCategoryById(String id);


}
