package backend.api.controller;

import backend.model.Post;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.reqResModel.post.GetAllActivePostResponse;
import backend.service.serviceInterface.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/post")
@RestController
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping("add-post")
    public CreatePostResponse addPost(@RequestBody @Valid CreatePostRequest createPostRequest){
        return this.postService.addPost(createPostRequest);
    }

    @GetMapping("get-all")
    public List<GetAllActivePostResponse> getAll(){
        return this.postService.getAllActivePosts();
    }

}
