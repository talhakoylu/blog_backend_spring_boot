package backend.service.reqResModel.category.updateCategory;

import backend.core.validations.UUIDValidation.UUIDValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequestImageModel {

    @UUIDValidation
    private String id;

}
