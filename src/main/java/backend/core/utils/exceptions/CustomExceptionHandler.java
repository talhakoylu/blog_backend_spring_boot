package backend.core.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MappingException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleMappingException(MappingException mappingException){
        return new ProblemDetails("MappingException", mappingException.getMessage());
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleBusinessRuleException(BusinessRuleException businessRuleException){
        return new ProblemDetails("BusinessRuleException", businessRuleException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ProblemDetails handleNotFoundException(NotFoundException notFoundException){
        return new ProblemDetails("NotFoundException", notFoundException.getMessage());
    }

    @ExceptionHandler(SlugHelperException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleSlugHelperException(SlugHelperException slugHelperException){
        return new ProblemDetails("SlugConvertError", slugHelperException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetailsWithErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){

        List<Map<String, String>> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(item -> Map.of("field", item.getField(), "message", item.getDefaultMessage())).toList();

        ProblemDetailsWithErrors problem = new ProblemDetailsWithErrors("ValidationError",
                "Validation error. Check 'errors' field for details.",
                errors);
        return problem;
    }

    @ExceptionHandler(FileUploadServiceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleFileUploadServiceException(FileUploadServiceException fileUploadServiceException){
        return new ProblemDetails("fileUploadServiceException", fileUploadServiceException.getMessage());
    }

}
