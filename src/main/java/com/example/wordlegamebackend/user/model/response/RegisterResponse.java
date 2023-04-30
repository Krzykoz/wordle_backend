package com.example.wordlegamebackend.user.model.response;

import com.example.wordlegamebackend.user.model.entity.User;

public record RegisterResponse(User user, String token) {
}
