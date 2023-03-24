package backend.repository;

import backend.common.PostStatusEnum;
import backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Boolean existsBySlug(String slug);

    Optional<Category> findByIdAndIsActiveTrue(UUID uuid);

    @Query("SELECT c FROM Category c JOIN FETCH c.posts p WHERE c.slug = :slug and c.isActive = :isActive and p.postStatus = :postStatus")
    Optional<Category> findCategoryBySlugWithIsActiveAndPostStatus(@Param("slug") String slug, @Param("isActive") boolean isActive, @Param("postStatus") PostStatusEnum posts_postStatus);

    @Query("SELECT c FROM Category c JOIN FETCH c.posts p WHERE c.id = :id and p.postStatus = :postStatus")
    Optional<Category> findCategoryByIdWithPostStatus(@Param("id") UUID id, @Param("postStatus") PostStatusEnum postStatusEnum);


}
