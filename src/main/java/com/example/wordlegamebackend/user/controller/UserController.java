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

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public RegisterResponse addUser(@RequestBody RegisterRequest request) {
        return userService.signUpNewUser(request);
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public UserDto getLoggedUser(Authentication authentication) {
        return userService.getCurrentUser(authentication);
    }

}
