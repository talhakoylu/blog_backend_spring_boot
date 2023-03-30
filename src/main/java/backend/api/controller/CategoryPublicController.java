package backend.api.controller;

import backend.core.apiResponse.ApiResponse;
import backend.service.reqResModel.category.getAllActiveCategories.GetAllActiveCategoriesResponse;
import backend.service.reqResModel.category.getCategoryDetailsBySlug.GetCategoryDetailsBySlugResponse;
import backend.service.serviceInterface.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Validated
@AllArgsConstructor
public class CategoryPublicController {

    private CategoryService categoryService;

    @GetMapping("get-category-details-by-slug")
    @Operation(summary = "Get details of category by slug value.", description = "Return a json object that include details of the category which found by slug value.")
    public ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlug(@RequestParam("slug") String slug) {
        return this.categoryService.getCategoryDetailsBySlug(slug);
    }

    @GetMapping("")
    @Operation(
            summary = "Get All Active Categories",
            description = "Return the list of all active categories."
    )
    public ResponseEntity<ApiResponse<List<GetAllActiveCategoriesResponse>>> getAllActiveCategories(){
        return this.categoryService.getAllCategories();
    }

}
