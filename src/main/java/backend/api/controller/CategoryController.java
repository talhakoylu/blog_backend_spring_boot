package backend.api.controller;

import backend.core.apiResponse.ApiResponse;
import backend.core.validations.UUIDValidation.UUIDValidation;
import backend.service.reqResModel.category.*;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryRequest;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryResponse;
import backend.service.serviceInterface.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/category")
@RestController
@AllArgsConstructor
@Validated
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("")
    @Operation(summary = "Add a category.", description = "Endpoint to serve category add operation.")
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> addCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
        return this.categoryService.addCategory(createCategoryRequest);
    }

    @GetMapping("get-category-details-by-slug")
    @Operation(summary = "Get details of category by slug value.", description = "Return a json object that include details of the category which found by slug value.")
    public ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlug(@RequestParam("slug") String slug){
        return this.categoryService.getCategoryDetailsBySlug(slug);
    }

    @PutMapping("")
    @Operation(summary = "Update a category", description = "Endpoint to update category and return a response. You must send coverImageId value, otherwise it will be set as null.")
    public ResponseEntity<ApiResponse<UpdateCategoryResponse>> updateCategory(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest){
        return this.categoryService.updateCategory(updateCategoryRequest);
    }

    @GetMapping("")
    @Operation(summary = "Get category by id (for delete request pages on client)", description = "This is the endpoint that created to use in delete request pages on client side. Returns only the record whose isActive value is true. In the posts array inside it, only values with postStatus.ACTIVE are listed.")
    public ResponseEntity<ApiResponse<GetCategoryByIdResponse>> getCategoryById(@RequestParam("id") @Valid @UUIDValidation String id){
        return this.categoryService.getCategoryById(id);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Soft Delete a Category By Id", description = "This endpoint will call services to change isActive value of category to false. So, after this that category will not shown on lists.")
    public ResponseEntity<ApiResponse<SoftDeleteCategoryByIdResponse>> softDeleteById(@RequestParam("id") @Valid @UUIDValidation String id){
        return this.categoryService.softDeleteById(id);
    }

}
