package backend.service.businessRules;

import backend.core.utils.exceptions.BusinessRuleException;
import backend.core.utils.exceptions.NotFoundException;
import backend.repository.CategoryRepository;
import backend.repository.ImageRepository;
import backend.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PostBusinessRules {

    private PostRepository postRepository;

    private CategoryRepository categoryRepository;

    private ImageRepository imageRepository;

    public void checkIfSlugExists(String slug) {
        if (this.postRepository.existsBySlug(slug)){
            throw new BusinessRuleException("Slug must be unique.");
        }
    }

    public void checkCoverImageExists(String id){
        if( id != null && !this.imageRepository.existsById(UUID.fromString(id))){
            throw new NotFoundException("No image found with this parameter.");
        }
    }

    public void checkCategoryExists(String id){
        if( id != null && !this.categoryRepository.existsById(UUID.fromString(id))){
            throw new NotFoundException("No category found with this parameter.");
        }
    }

}
