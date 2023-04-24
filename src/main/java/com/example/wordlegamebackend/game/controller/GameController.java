package com.example.wordlegamebackend.game.controller;

import com.example.wordlegamebackend.game.model.Game;
import com.example.wordlegamebackend.game.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    //test
    @PostMapping
    public void add(@RequestBody Game game){
        gameService.addNewGame(game);
    }
}
