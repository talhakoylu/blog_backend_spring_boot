package backend.service.serviceInterface;

import backend.model.Post;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.reqResModel.post.GetAllActivePostResponse;

import java.util.List;

public interface PostService {

    CreatePostResponse addPost(CreatePostRequest createPostRequest);

//    List<GetAllActivePostResponse> getAllActivePosts();
    List<GetAllActivePostResponse> getAllActivePosts();

}
