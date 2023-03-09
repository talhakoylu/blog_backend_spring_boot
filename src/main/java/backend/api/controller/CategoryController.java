package backend.api.controller;

import backend.model.Category;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.reqResModel.category.GetAllCategoryResponse;
import backend.service.reqResModel.category.GetByIdCategoryResponse;
import backend.service.serviceInterface.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("add")
    public CreateCategoryResponse add(@RequestBody() CreateCategoryRequest createCategoryRequest){
        return this.categoryService.add(createCategoryRequest);
    }

    @GetMapping("get-by-id")
    public GetByIdCategoryResponse getById(@RequestBody() String id){
        return this.categoryService.findById(id);
    }

    @GetMapping("get-all")
    public List<GetAllCategoryResponse> getAllCategories(){
        return this.categoryService.getAllCategories();
    }


}
