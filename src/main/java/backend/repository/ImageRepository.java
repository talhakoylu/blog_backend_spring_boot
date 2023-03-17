package backend.repository;

import backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {

    Optional<Image> findByIdAndIsActiveTrue(UUID id);

    Optional<Image> findByImageUniqueName(String imagePath);

}
