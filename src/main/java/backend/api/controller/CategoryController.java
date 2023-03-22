package backend.api.controller;

import backend.core.apiResponse.ApiResponse;
import backend.service.reqResModel.category.*;
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
        return this.categoryService.getCategoryDetailsBySlugAndIsActive(slug);
    }

    @PutMapping("")
    @Operation(summary = "Update a category", description = "Endpoint to update category and return a response.")
    public ResponseEntity<ApiResponse<UpdateCategoryResponse>> updateCategory(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest){
        return this.categoryService.updateCategory(updateCategoryRequest);
    }

}
