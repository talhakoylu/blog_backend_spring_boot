package backend.repository;

import backend.common.PostStatusEnum;
import backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Boolean existsBySlug(String slug);

    List<Post> findAllByPostStatus(PostStatusEnum postStatusEnum);

    default List<Post> findAllByPostStatusActive(){
        return this.findAllByPostStatus(PostStatusEnum.ACTIVE);
    }

}
