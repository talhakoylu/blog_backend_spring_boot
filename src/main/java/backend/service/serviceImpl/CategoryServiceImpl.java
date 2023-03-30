package backend.service.serviceImpl;

import backend.common.PostStatusEnum;
import backend.core.apiResponse.ApiResponse;
import backend.core.apiResponse.ResponseHelper;
import backend.core.utils.exceptions.NotFoundException;
import backend.model.Category;
import backend.model.Post;
import backend.repository.CategoryRepository;
import backend.service.businessRules.CategoryBusinessRules;
import backend.service.mapper.CategoryMapper;
import backend.service.reqResModel.category.*;
import backend.service.reqResModel.category.hardDeleteCategoryById.HardDeleteCategoryByIdResponse;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryRequest;
import backend.service.reqResModel.category.updateCategory.UpdateCategoryResponse;
import backend.service.serviceInterface.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Returns only the record whose isActive value is true. In the posts array inside it, only values with postStatus.ACTIVE are listed.
     *
     * @param slug request slug value
     * @return GetCategoryDetailsBySlugResponse
     */
    @Override
    public ResponseEntity<ApiResponse<GetCategoryDetailsBySlugResponse>> getCategoryDetailsBySlug(String slug) {

        Category category = this.categoryRepository.findCategoryBySlugAndIsActiveIsTrue(slug).orElseThrow(() -> new NotFoundException("Category not found."));

        category.setPosts(category.getPosts().stream().filter(item -> item.getPostStatus().equals(PostStatusEnum.ACTIVE)).toList());

        GetCategoryDetailsBySlugResponse result = this.categoryMapper.categoryToGetCategoryDetailsBySlugResponse(category);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Category details have been introduced.", result);
    }

    @Override
    public ResponseEntity<ApiResponse<UpdateCategoryResponse>> updateCategory(UpdateCategoryRequest updateCategoryRequest) {

        this.categoryBusinessRules.checkCoverImageExistsAndActive(updateCategoryRequest.getCoverImage() != null ? updateCategoryRequest.getCoverImage().getId() : null);

        Category category = this.categoryRepository.findById(UUID.fromString(updateCategoryRequest.getId())).orElseThrow(
                () -> new NotFoundException("Category not found.")
        );

        String slugOfCategory = category.getSlug();

        category = this.categoryMapper.updateCategoryRequestAndCategoryToCategory(category, updateCategoryRequest);

        this.categoryBusinessRules.checkIfSlugExists(category.getSlug(), slugOfCategory);

        Category result = this.categoryRepository.save(category);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Category updated successfully.",
                this.categoryMapper.categoryToUpdateCategoryResponse(result));
    }

    /**
     * A service method to use for delete request pages on client side.
     *
     * @param id UUID id value
     * @return ResponseEntity<ApiResponse < GetCategoryByIdResponse>>
     */
    @Override
    public ResponseEntity<ApiResponse<GetCategoryByIdResponse>> getCategoryById(String id) {

        Category findResult = this.categoryRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("Category not found."));

        findResult.setPosts(findResult.getPosts().stream().filter(item -> item.getPostStatus().equals(PostStatusEnum.ACTIVE)).toList());

        GetCategoryByIdResponse response = this.categoryMapper.categoryToGetCategoryByIdResponse(findResult);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Category found.", response);
    }

    @Override
    public ResponseEntity<ApiResponse<SoftDeleteCategoryByIdResponse>> softDeleteById(String id) {

        Category category = this.categoryRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("Category not found."));

        this.categoryBusinessRules.checkCategoryIsAlreadyInactive(category);

        List<Post> postsOfCategory = new ArrayList<>(category.getPosts().stream()
                .filter(item -> item.getPostStatus() == PostStatusEnum.ACTIVE)
                .peek(item -> item.setPostStatus(PostStatusEnum.TASK)).toList());

        category.setIsActive(false);

        category.setPosts(postsOfCategory);

        category = this.categoryRepository.save(category);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Category successfully removed.",
                this.categoryMapper.categoryToSoftDeleteIdResponse(category));
    }

    @Override
    public ResponseEntity<ApiResponse<HardDeleteCategoryByIdResponse>> hardDeleteById(String id) {

        Category category = this.categoryRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("Category not found."));

        this.categoryBusinessRules.checkCategoryIsActive(category);

        List<Post> postList = new ArrayList<>(category.getPosts().stream().peek(item -> {
            item.setCategory(null);
            if(item.getPostStatus().equals(PostStatusEnum.ACTIVE)){
                item.setPostStatus(PostStatusEnum.TASK);
            }
        }).toList());

        category.setPosts(postList);

        System.out.println(category.getPosts());

        this.categoryRepository.delete(category);

        return this.responseHelper.buildResponse(HttpStatus.OK.value(), "Category removed.",
                this.categoryMapper.categoryToHardDeleteCategoryByIdResponse(category));
    }
}
