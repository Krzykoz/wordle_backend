package com.piaskowy.urlshortenerbackend.user.exception.userExceptionHandler;

import com.piaskowy.urlshortenerbackend.global.BaseExceptionHandler;
import com.piaskowy.urlshortenerbackend.user.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(value = {
            UserAlreadyExistsException.class,
            UsernameNotFoundException.class
    })
    @Override
    public ResponseEntity<Object> handleForbiddenExceptions(final RuntimeException ex, final WebRequest request) {
        return super.handleForbiddenExceptions(ex, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Override
    public ResponseEntity<Object> handleValidationExceptions(final MethodArgumentNotValidException ex, final WebRequest request) {
        return super.handleValidationExceptions(ex, request);
    }
}
