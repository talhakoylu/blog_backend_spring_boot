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

    public void checkCoverImageExists(UUID id){
        if( id != null && !this.imageRepository.existsByIdAndIsActiveTrue(id)){
            throw new NotFoundException("Image not found or could be inactive.");
        }
    }

    public void checkCategoryExists(UUID id){
        if( id != null && !this.categoryRepository.existsByIdAndIsActiveTrue(id)){
            throw new NotFoundException("Category not found or could be inactive.");
        }
    }

}
