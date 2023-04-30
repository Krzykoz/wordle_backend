package com.example.wordlegamebackend;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(LocalDateTime timestamp, String message, String path) {
}
