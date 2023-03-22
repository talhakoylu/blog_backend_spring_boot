package backend.service.serviceImpl;

import backend.common.PostStatusEnum;
import backend.core.apiResponse.ApiResponse;
import backend.core.apiResponse.ResponseHelper;
import backend.core.utils.exceptions.NotFoundException;
import backend.model.Category;
import backend.repository.CategoryRepository;
import backend.service.businessRules.CategoryBusinessRules;
import backend.service.mapper.CategoryMapper;
import backend.service.reqResModel.category.*;
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
        return this.categoryRepository.findByIdAndIsActiveTrue(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("No category found with this parameter or category is not active."));
    }

    @Override
    public ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlugAndIsActive(String slug) {

        Category category = this.categoryRepository.findBySlugAndIsActiveTrueAndPosts_postStatusIs(slug, PostStatusEnum.ACTIVE).orElseThrow( () -> new NotFoundException("Category not found."));

        GetCategoryDetailsBySlugResponse result = this.categoryMapper.categoryToGetCategoryDetailsBySlugResponse(category);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Category details have been introduced.", result);
    }

    @Override
    public ResponseEntity<ApiResponse<UpdateCategoryResponse>> updateCategory(UpdateCategoryRequest updateCategoryRequest) {

        Category category = this.categoryRepository.findById(UUID.fromString(updateCategoryRequest.getId())).orElseThrow(
                () -> new NotFoundException("Category not found.")
        );

        String slugOfCategory = category.getSlug();

        category = this.categoryMapper.updateCategoryRequestAndCategoryToCategory(category, updateCategoryRequest);

        this.categoryBusinessRules.checkIfSlugExists(category.getSlug(), slugOfCategory);

        category = this.categoryRepository.save(category);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Category updated successfully.",
                this.categoryMapper.categoryToUpdateCategoryResponse(category));
    }
}
