package backend.service.serviceImpl;

import backend.common.PostStatusEnum;
import backend.core.apiResponse.ApiResponse;
import backend.core.apiResponse.ResponseHelper;
import backend.model.Post;
import backend.repository.PostRepository;
import backend.service.businessRules.PostBusinessRules;
import backend.service.mapper.PostMapper;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.serviceInterface.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private PostBusinessRules postBusinessRules;

    private PostMapper postMapper;

    private ResponseHelper responseHelper;

    @Override
    public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(CreatePostRequest createPostRequest) {

        this.postBusinessRules.checkCoverImageExists(createPostRequest.getCoverImage() != null ? createPostRequest.getCoverImage().getId() : null);
        this.postBusinessRules.checkCategoryExists(createPostRequest.getCategory() != null ? createPostRequest.getCategory().getId() : null);

        Post post = this.postMapper.createPostRequestToPost(createPostRequest);

        this.postBusinessRules.checkIfSlugExists(post.getSlug());

        Post result = this.postRepository.save(post);

        return this.responseHelper.buildResponse(HttpStatus.CREATED.value(),
                "Post added successfully",
                this.postMapper.postToCreatePostResponse(result));
    }

    @Override
    public void updateAll(List<Post> posts) {
        this.postRepository.saveAll(posts);

    }
}
