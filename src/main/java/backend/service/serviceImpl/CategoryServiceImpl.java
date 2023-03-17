package backend.service.serviceImpl;

import backend.core.apiResponse.ApiResponse;
import backend.core.apiResponse.ResponseHelper;
import backend.core.utils.exceptions.NotFoundException;
import backend.model.Category;
import backend.repository.CategoryRepository;
import backend.service.businessRules.CategoryBusinessRules;
import backend.service.mapper.CategoryMapper;
import backend.service.reqResModel.category.CreateCategoryRequest;
import backend.service.reqResModel.category.CreateCategoryResponse;
import backend.service.serviceInterface.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    private CategoryBusinessRules categoryBusinessRules;

    private ResponseHelper responseHelper;

    @Override
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> addCategory(CreateCategoryRequest createCategoryRequest) {

        this.categoryBusinessRules.checkCoverImageExists(createCategoryRequest.getCoverImageId());

        Category category = this.categoryMapper.createCategoryRequestToCategory(createCategoryRequest);

        this.categoryBusinessRules.checkIfSlugExists(category.getSlug());

        Category result = this.categoryRepository.save(category);

        return this.responseHelper.buildResponse(HttpStatus.CREATED.value(), "Category added.", this.categoryMapper.categoryToCreateCategoryResponse(result));
    }

    @Override
    public Category findByIdForMapper(String id) {
        return this.categoryRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No category found with this parameter."));
    }
}
