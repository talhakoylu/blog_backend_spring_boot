package backend.service.serviceInterface;

import backend.core.apiResponse.ApiResponse;
import backend.model.Category;
import backend.service.reqResModel.category.createCategory.CreateCategoryRequest;
import backend.service.reqResModel.category.createCategory.CreateCategoryResponse;
import backend.service.reqResModel.category.getAllActiveCategories.GetAllActiveCategoriesResponse;
import backend.service.reqResModel.category.getCategoryById.GetCategoryByIdResponse;
import backend.service.reqResModel.category.getCategoryDetailsBySlug.GetCategoryDetailsBySlugResponse;
import backend.service.reqResModel.category.hardDeleteCategoryById.HardDeleteCategoryByIdResponse;
import backend.service.reqResModel.category.softDeleteCategoryById.SoftDeleteCategoryByIdResponse;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryRequest;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    ResponseEntity<ApiResponse<CreateCategoryResponse>> addCategory(CreateCategoryRequest createCategoryRequest);

    Category findByIdForMapper(String id);

    ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlug(String slug);

    ResponseEntity<ApiResponse<UpdateCategoryResponse>> updateCategory(UpdateCategoryRequest updateCategoryRequest);

    ResponseEntity<ApiResponse<GetCategoryByIdResponse>> getCategoryById(String id);

    ResponseEntity<ApiResponse<SoftDeleteCategoryByIdResponse>> softDeleteById(String id);

    ResponseEntity<ApiResponse<HardDeleteCategoryByIdResponse>> hardDeleteById(String id);

    ResponseEntity<ApiResponse<List<GetAllActiveCategoriesResponse>>> getAllCategories();


}
