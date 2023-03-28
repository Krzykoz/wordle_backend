package com.example.wordlegamebackend.word.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LanguageAlreadyExistsException extends RuntimeException {
    public LanguageAlreadyExistsException(final String message) {
        super(message);
    }
}
