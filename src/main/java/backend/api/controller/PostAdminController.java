package backend.api.controller;

import backend.core.apiResponse.ApiResponse;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.serviceInterface.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admin/post")
@RestController
@AllArgsConstructor
@Validated
public class PostAdminController {

    private PostService postService;

    @PostMapping("create-post")
    public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(@RequestBody @Valid CreatePostRequest createPostRequest){
        return this.postService.createPost(createPostRequest);
    }

}
