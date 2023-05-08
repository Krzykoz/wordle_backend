package com.example.wordlegamebackend.user.controller;

import com.example.wordlegamebackend.user.model.dto.UserDto;
import com.example.wordlegamebackend.user.model.request.RegisterRequest;
import com.example.wordlegamebackend.user.model.response.RegisterResponse;
import com.example.wordlegamebackend.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    /**
     * The UserService instance for performing user-related operations.
     */
    private final UserService userService;

    /**
     * Constructs a new UserController with the given UserService.
     *
     * @param userService the UserService instance to be used for user-related operations.
     */
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles the registration of a new user.
     *
     * @param request the RegisterRequest containing the details of the user to be registered.
     * @return the RegisterResponse containing the details of the newly registered user.
     */
    @PostMapping
    public RegisterResponse addUser(@RequestBody RegisterRequest request) {
        return userService.signUpNewUser(request);
    }

    /**
     * Retrieves the details of a user with the given ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the UserDto containing the details of the user with the given ID.
     */
    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Retrieves the details of the currently logged in user.
     *
     * @param authentication the Authentication object containing the details of the currently logged in user.
     * @return the UserDto containing the details of the currently logged in user.
     */
    @GetMapping("/user")
    public UserDto getLoggedUser(Authentication authentication) {
        return userService.getCurrentUser(authentication);
    }

}
