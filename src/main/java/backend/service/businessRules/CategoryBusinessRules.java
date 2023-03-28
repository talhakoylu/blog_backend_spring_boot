package backend.service.businessRules;

import backend.core.utils.exceptions.BusinessRuleException;
import backend.core.utils.exceptions.NotFoundException;
import backend.model.Category;
import backend.repository.CategoryRepository;
import backend.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {

    private CategoryRepository categoryRepository;
    private ImageRepository imageRepository;

    public void checkIfSlugExists(String slug){
        if(this.categoryRepository.existsBySlug(slug)){
            throw new BusinessRuleException("Slug must be unique.");
        }
    }

    /**
     * Check the slug if exists or not. Will throw a BusinessRuleException if requestSlug and existsSlug are not same and new Slug is exists.
     * @param requestSlug Request slug value
     * @param existingSlug Providing slug value by existing record.
     */
    public void checkIfSlugExists(String requestSlug, String existingSlug){
        if(!existingSlug.equals(requestSlug) && this.categoryRepository.existsBySlug(requestSlug)){
            throw new BusinessRuleException("Slug must be unique.");
        }
    }

    public void checkCoverImageExists(String id){
        if( id != null && !this.imageRepository.existsById(UUID.fromString(id))){
            throw new NotFoundException("No image found with this parameter.");
        }
    }

    public void checkCoverImageExistsAndActive(String id){
        if (id != null && !this.imageRepository.existsByIdAndIsActiveTrue(UUID.fromString(id))){
            throw new BusinessRuleException("Image not found or could be inactive.");
        }
    }

    public void checkCategoryIsAlreadyInactive(Category category){
        if(!category.getIsActive()){
            throw new BusinessRuleException("Category is already removed.");
        }
    }

}
