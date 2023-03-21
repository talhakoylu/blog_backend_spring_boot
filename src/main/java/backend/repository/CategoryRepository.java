package backend.repository;

import backend.common.PostStatusEnum;
import backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Boolean existsBySlug(String slug);


    Optional<Category> findBySlugAndIsActiveTrueAndPosts_postStatusIs(String slug, PostStatusEnum posts_postStatus);

}
