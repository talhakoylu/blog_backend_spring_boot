package backend.api.controller;

import backend.core.apiResponse.ApiResponse;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.serviceInterface.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/post")
@RestController
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping("create-post")
    public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(CreatePostRequest createPostRequest){
        return this.postService.createPost(createPostRequest);
    }

}
