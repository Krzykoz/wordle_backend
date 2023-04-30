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
