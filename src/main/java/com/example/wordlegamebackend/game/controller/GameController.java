package com.example.wordlegamebackend.game.controller;

import com.example.wordlegamebackend.game.model.request.AddGameRequest;
import com.example.wordlegamebackend.game.service.GameService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/game")
public class GameController {

    private final GameService gameService;

    public GameController(@RequestBody GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public void add(@RequestBody AddGameRequest request, Authentication authentication) {
        gameService.addNewGame(request, authentication);
    }
}
