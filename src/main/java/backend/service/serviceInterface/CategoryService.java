package backend.service.serviceInterface;

import backend.model.Category;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.reqResModel.category.GetAllCategoryResponse;
import backend.service.reqResModel.category.GetByIdCategoryResponse;

import java.util.List;

public interface CategoryService {

    CreateCategoryResponse add(CreateCategoryRequest createCategoryRequest);

    GetByIdCategoryResponse findById(String id);

    List<GetAllCategoryResponse> getAllCategories();

    Category findByIdForMapper(String id);


}
