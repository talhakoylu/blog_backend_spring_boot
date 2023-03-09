package backend.service.businessRules;

import backend.core.utils.exceptions.BusinessRuleException;
import backend.repository.PostRepository;
import backend.service.serviceInterface.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostBusinessRules {

    private PostRepository postRepository;

    public void checkIfSlugExists(String slug) {
        if (this.postRepository.existsBySlug(slug)){
            throw new BusinessRuleException("Slug must be unique.");
        }
    }

}
