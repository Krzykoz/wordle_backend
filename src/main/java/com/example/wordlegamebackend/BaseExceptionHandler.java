package com.example.wordlegamebackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The BaseExceptionHandler abstract class provides methods for handling common exception types
 * in a standardized manner.
 */
public abstract class BaseExceptionHandler {

    /**
     * Handle Forbidden Exceptions and return a ResponseEntity with a standardized error message.
     * @param ex the runtime exception to handle
     * @param request the web request being handled
     * @return a ResponseEntity containing the error message and HTTP status code
     */
    public ResponseEntity<Object> handleForbiddenExceptions(RuntimeException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ErrorResponse exceptionMessage = ErrorResponse.builder()
                .message(ex.getMessage())
                .path(requestUri)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle Not Found Exceptions and return a ResponseEntity with a standardized error message.
     * @param ex the runtime exception to handle
     * @param request the web request being handled
     * @return a ResponseEntity containing the error message and HTTP status code
     */
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ErrorResponse exceptionMessage = ErrorResponse.builder()
                .message(ex.getMessage())
                .path(requestUri)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle Validation Exceptions and return a ResponseEntity with a standardized error message.
     * @param ex the MethodArgumentNotValidException to handle
     * @param request the web request being handled
     * @return a ResponseEntity containing the error message and HTTP status code
     */
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse exceptionMessage = ErrorResponse.builder()
                .message(errors.values().toString())
                .path(requestUri)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionMessage, HttpStatus.FORBIDDEN);
    }

}
