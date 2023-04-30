package com.example.wordlegamebackend.game.model.request;

public record AddGameRequest(int turns, boolean guessed, String word, String languageCode) {
}
