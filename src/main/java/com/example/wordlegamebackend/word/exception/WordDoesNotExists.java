package com.example.wordlegamebackend.word.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WordDoesNotExists extends RuntimeException {
    public WordDoesNotExists(final String message) {
        super(message);
    }
}
