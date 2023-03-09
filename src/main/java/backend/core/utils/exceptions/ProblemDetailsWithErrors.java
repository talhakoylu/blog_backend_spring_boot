package backend.core.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProblemDetailsWithErrors extends ProblemDetails{
    private List<Map<String, String>> errors;

    public ProblemDetailsWithErrors(String errorType, String message, List<Map<String, String>> errors) {
        super(errorType, message);
        this.errors = errors;
    }
}
