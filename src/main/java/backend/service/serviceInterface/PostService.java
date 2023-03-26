package backend.service.serviceInterface;

import backend.core.apiResponse.ApiResponse;
import backend.model.Post;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseEntity<ApiResponse<CreatePostResponse>> createPost(CreatePostRequest createPostRequest);

    void updateAll(List<Post> posts);

}
