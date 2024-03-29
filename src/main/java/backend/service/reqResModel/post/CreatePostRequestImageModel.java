package backend.service.reqResModel.post;

import backend.core.validations.UUIDValidation.UUIDValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequestImageModel {

    @UUIDValidation
    private String id;

}
