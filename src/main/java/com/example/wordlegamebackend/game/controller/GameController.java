/**
 * This class represents the REST controller for game-related operations.
 */

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

    /**
     * Constructs a new instance of the GameController class with the specified GameService instance.
     *
     * @param gameService the GameService instance used by this controller
     */
    public GameController(@RequestBody GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Adds a new game to the system using the specified request and authentication information.
     *
     * @param request the AddGameRequest instance containing the game data
     * @param authentication the authentication information for the current user
     */
    @PostMapping
    public void add(@RequestBody AddGameRequest request, Authentication authentication) {
        gameService.addNewGame(request, authentication);
    }
}
