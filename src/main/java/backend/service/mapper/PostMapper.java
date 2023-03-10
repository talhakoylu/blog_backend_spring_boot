package backend.service.mapper;

import backend.common.PostStatusEnum;
import backend.core.utils.exceptions.MappingException;
import backend.service.serviceInterface.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Mapper(componentModel = "spring")
@Service
public abstract class PostMapper {

    @Autowired
    protected ImageService imageService;

    @Named("stringToStatus")
    protected PostStatusEnum stringToStatus(String postStatus) {
        String status = postStatus.toUpperCase(Locale.ENGLISH);

        return switch (status) {
            case "ACTIVE" -> PostStatusEnum.ACTIVE;
            case "TASK" -> PostStatusEnum.TASK;
            case "REMOVED" -> PostStatusEnum.REMOVED;
            default -> throw new MappingException("Post status does not have correct value.");
        };

    }



}
