/**
    This class represents the service for handling game-related operations.
    The GameService class provides the following functionality:
        addNewGame: adds a new game to the system with the given request data and authentication details
    The GameService class depends on the following classes:
        Game: the entity representing a game in the system
        AddGameRequest: the request data for adding a new game to the system
        GameRepository: the repository for storing and retrieving game entities
        CustomUserDetails: the custom user details class used for authentication
        User: the entity representing a user in the system
        Word: the entity representing a word in the system
        WordService: the service for handling word-related operations
*/

package com.example.wordlegamebackend.game.service;

import com.example.wordlegamebackend.game.model.Game;
import com.example.wordlegamebackend.game.model.request.AddGameRequest;
import com.example.wordlegamebackend.game.repository.GameRepository;
import com.example.wordlegamebackend.user.model.CustomUserDetails;
import com.example.wordlegamebackend.user.model.entity.User;
import com.example.wordlegamebackend.word.model.entity.Word;
import com.example.wordlegamebackend.word.service.WordService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final WordService wordService;

    public GameService(GameRepository gameRepository, final WordService wordService) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

    /**
     * Adds a new game to the system with the given request data and authentication details.
     *
     * @param addGameRequest the request data for adding a new game
     * @param authentication the authentication details of the user adding the game
     */
    public void addNewGame(AddGameRequest addGameRequest, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.user();

        Word word = wordService.findWordByWordAndLanguage(addGameRequest.word(), addGameRequest.languageCode());

        Game game = Game
                .builder()
                .guessed(addGameRequest.guessed())
                .turns(addGameRequest.turns())
                .user(user)
                .word(word)
                .build();

        gameRepository.save(game);
    }
}
