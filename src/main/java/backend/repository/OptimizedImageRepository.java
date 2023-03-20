package backend.repository;

import backend.model.OptimizedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OptimizedImageRepository extends JpaRepository<OptimizedImage, UUID> {
}
