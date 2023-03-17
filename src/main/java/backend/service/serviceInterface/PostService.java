package backend.service.serviceInterface;

import backend.core.apiResponse.ApiResponse;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {

    ResponseEntity<ApiResponse<CreatePostResponse>> createPost(CreatePostRequest createPostRequest);

}
