package com.example.wordlegamebackend.game.service;

import com.example.wordlegamebackend.game.model.Game;
import com.example.wordlegamebackend.game.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void addNewGame(Game game){
        gameRepository.save(game);
    }
}
