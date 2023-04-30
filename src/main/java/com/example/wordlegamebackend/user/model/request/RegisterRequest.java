package com.example.wordlegamebackend.user.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record RegisterRequest(
        @Email String email,
        @NotBlank(message = "Name can not be blank")
        String firstName,
        @NotBlank(message = "Lastname can not be blank")
        String lastName
) {
}
