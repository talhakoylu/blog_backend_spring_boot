package backend.service.serviceImpl;

import backend.core.utils.exceptions.MappingException;
import backend.core.utils.exceptions.NotFoundException;
import backend.model.Category;
import backend.repository.CategoryRepository;
import backend.service.mapper.CategoryMapper;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.reqResModel.category.GetAllCategoryResponse;
import backend.service.reqResModel.category.GetByIdCategoryResponse;
import backend.service.serviceInterface.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest createCategoryRequest) {
        Category toCategory = this.categoryMapper.createCategoryRequestToCategory(createCategoryRequest);

        Category result = this.categoryRepository.save(toCategory);


        return this.categoryMapper.categoryToCreateCategoryResponse(result);
    }

    @Override
    public GetByIdCategoryResponse findById(String id) {
        Category result = this.categoryRepository.getReferenceById(UUID.fromString(id));
        return this.categoryMapper.categoryToGetByIdCategoryResponse(result);
    }

    @Override
    public List<GetAllCategoryResponse> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll().stream().toList();

        return this.categoryMapper.categoryToGetAllCategoryListResponse(categories);
    }

    @Override
    public Category findByIdForMapper(String id) {
        return this.categoryRepository.findById(UUID.fromString(id)).orElseThrow( () -> new NotFoundException("No category with this parameter was found."));
    }


}
