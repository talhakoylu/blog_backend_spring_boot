package backend.api.controller;

import backend.core.apiResponse.ApiResponse;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.reqResModel.category.GetCategoryDetailsBySlugResponse;
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

    @PostMapping("add-category")
    @Operation(summary = "Add a category.", description = "Endpoint to serve category add operation.")
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> addCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
        return this.categoryService.addCategory(createCategoryRequest);
    }

    @GetMapping("get-category-details-by-slug")
    @Operation(summary = "Get details of category by slug value.", description = "Return a json object that include details of the category which found by slug value.")
    public ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlug(@RequestParam("slug") String slug){
        return this.categoryService.getCategoryDetailsBySlugAndIsActive(slug);
    }

}
