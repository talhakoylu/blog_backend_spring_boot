package backend.service.serviceImpl;

import backend.model.Post;
import backend.repository.PostRepository;
import backend.service.businessRules.PostBusinessRules;
import backend.service.mapper.PostMapper;
import backend.service.reqResModel.post.CreatePostRequest;
import backend.service.reqResModel.post.CreatePostResponse;
import backend.service.reqResModel.post.GetAllActivePostResponse;
import backend.service.serviceInterface.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private PostMapper postMapper;

    private PostBusinessRules postBusinessRules;

    @Override
    public CreatePostResponse addPost(CreatePostRequest createPostRequest) {
        Post toPost = this.postMapper.CreatePostRequestToPost(createPostRequest);

        this.postBusinessRules.checkIfSlugExists(toPost.getSlug());

        Post result = this.postRepository.save(toPost);

        return this.postMapper.postToCreatePostResponse(result);
    }

    public List<GetAllActivePostResponse> getAllActivePosts() {
        List<Post> posts = this.postRepository.findAllByPostStatusActive().stream().toList();

        List<GetAllActivePostResponse> results = this.postMapper.getAllActivePostResponseListMapper(posts);

        return results;
    }

}
