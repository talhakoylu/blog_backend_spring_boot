package backend.service.reqResModel.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoryResponse {

    private String title;

    private String description;

    private String slug;

    private Map<String, String> image = new HashMap<>();

}
