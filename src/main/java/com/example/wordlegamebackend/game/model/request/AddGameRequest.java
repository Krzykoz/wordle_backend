package com.example.wordlegamebackend.game.model.request;

/**
    This class represents the request data for adding a new game to the system.
    The AddGameRequest class is a record class that contains the following data:
        turns: the number of turns in the game
        guessed: a boolean indicating whether the word has been guessed
        word: the word being guessed in the game
        languageCode: the language code for the word being guessed
*/
public record AddGameRequest(int turns, boolean guessed, String word, String languageCode) {
}
