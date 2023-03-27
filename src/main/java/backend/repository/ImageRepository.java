package backend.repository;

import backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {

    Optional<Image> findByIdAndIsActiveTrue(UUID id);

    Boolean existsByIdAndIsActiveTrue(UUID id);

    @Query("SELECT i FROM Image i LEFT JOIN i.optimizedImages oi WHERE i.imageUniqueName = :name OR oi.uniqueName = :name")
    Optional<Image> findImageByUniqueNameInImagesOrOptimizedImages(@Param("name") String uniqueName);

}
