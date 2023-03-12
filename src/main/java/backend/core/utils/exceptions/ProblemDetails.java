package backend.core.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProblemDetails {

    private String errorType;

    private String messageDetail;
}
