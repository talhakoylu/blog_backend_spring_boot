package backend.core.apiResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ResponseHelper {

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(
            HttpHeaders httpHeader, int httpStatusCode, String message, T data, Map<String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <T> (httpStatusCode, message).withHttpHeader(httpHeader)
                .withData(data).withOtherParams(otherParams).build();
    }

    public <T> ResponseEntity <ApiResponse<T>> buildResponse(
            int httpStatusCode, String message, T data, Map <String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <T> (httpStatusCode, message)
                .withData(data).withOtherParams(otherParams).build();
    }

    public <T> ResponseEntity <ApiResponse<T>> buildResponse(
            int httpStatusCode, String message, Map <String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <T> (httpStatusCode, message)
                .withOtherParams(otherParams).build();
    }

    public <T> ResponseEntity <ApiResponse<T>> buildResponse(
            HttpHeaders httpHeader, int httpStatusCode, String message, T data) {
        return new ApiResponse.ApiResponseBuilder <T> (httpStatusCode, message)
                .withHttpHeader(httpHeader).withData(data).build();
    }

    public <T> ResponseEntity <ApiResponse<T>> buildResponse(
            HttpHeaders httpHeader, int httpStatusCode, String message, Map <String, Object> otherParams) {
        return new ApiResponse.ApiResponseBuilder <T> (httpStatusCode, message)
                .withHttpHeader(httpHeader).withOtherParams(otherParams).build();
    }

    public <T> ResponseEntity <ApiResponse<T>> buildResponse(
            HttpHeaders httpHeader, int httpStatusCode, String message) {
        return new ApiResponse.ApiResponseBuilder <T> (httpStatusCode, message)
                .withHttpHeader(httpHeader).build();
    }

    public <T> ResponseEntity <ApiResponse<T>> buildResponse(
            int httpStatusCode, String message, T data) {
        return new ApiResponse.ApiResponseBuilder <T> (httpStatusCode, message)
                .withData(data).build();
    }

}
