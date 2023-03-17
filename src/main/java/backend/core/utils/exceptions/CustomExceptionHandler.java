package backend.core.utils.exceptions;

import backend.core.apiResponse.ApiResponse;
import backend.core.apiResponse.ResponseHelper;
import jakarta.validation.ConstraintDefinitionException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
@ControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler {

    private ResponseHelper responseHelper;

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<ApiResponse<ProblemDetails>> handleMappingException(MappingException mappingException){
        return this.responseHelper.buildResponse(HttpStatus.BAD_REQUEST.value(), "Mapping Error",
                new ProblemDetails("MappingException", mappingException.getMessage()));
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiResponse<ProblemDetails>> handleBusinessRuleException(BusinessRuleException businessRuleException){
        return this.responseHelper.buildResponse(HttpStatus.BAD_REQUEST.value(), "Business Logic Error",
                new ProblemDetails("BusinessRuleException", businessRuleException.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<ProblemDetails>> handleNotFoundException(NotFoundException notFoundException){
        return this.responseHelper.buildResponse(HttpStatus.NOT_FOUND.value(), "Not Found Error",
                new ProblemDetails("NotFoundException", notFoundException.getMessage()));
    }

    @ExceptionHandler(SlugHelperException.class)
    public ResponseEntity<ApiResponse<ProblemDetails>> handleSlugHelperException(SlugHelperException slugHelperException){
        return this.responseHelper.buildResponse(HttpStatus.BAD_REQUEST.value(), "Slug Generation Error",
                new ProblemDetails("SlugConvertException", slugHelperException.getMessage()));
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ConstraintDefinitionException.class})
    public ResponseEntity<ApiResponse<ProblemDetailsWithErrors>> handleMethodArgumentNotValidException(BindException bindException){

        List<Map<String, String>> errors = bindException.getBindingResult().getFieldErrors().stream().map(item -> Map.of("field", item.getField(), "message", item.getDefaultMessage())).toList();

        return this.responseHelper.buildResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Validation Error",
                new ProblemDetailsWithErrors("ValidationError",
                        "Validation error. Check 'errors' field for details.",
                        errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<ProblemDetailsWithErrors>> handleConstraintViolationException(ConstraintViolationException constraintViolationException){

        List<Map<String, String>> errors = constraintViolationException.getConstraintViolations().stream().map(item -> Map.of("message", item.getMessage())).toList();

        return this.responseHelper.buildResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Validation Error",
                new ProblemDetailsWithErrors("ValidationError",
                        "Validation error. Check 'errors' field for details.",
                        errors));
    }


    @ExceptionHandler(FileUploadServiceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<ProblemDetails>> handleFileUploadServiceException(FileUploadServiceException fileUploadServiceException){
        return this.responseHelper.buildResponse(HttpStatus.NOT_ACCEPTABLE.value(), "File Upload Service Error",
                new ProblemDetails("fileUploadServiceException", fileUploadServiceException.getMessage()));
    }

}
