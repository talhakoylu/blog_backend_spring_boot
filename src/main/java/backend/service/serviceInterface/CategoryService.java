package backend.service.serviceInterface;

import backend.core.apiResponse.ApiResponse;
import backend.model.Category;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.reqResModel.category.GetCategoryDetailsBySlugResponse;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<ApiResponse<CreateCategoryResponse>> addCategory(CreateCategoryRequest createCategoryRequest);

    Category findByIdForMapper(String id);

    ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlugAndIsActive(String slug);

}
