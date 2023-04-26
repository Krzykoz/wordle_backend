package com.piaskowy.urlshortenerbackend.user.controller;

import com.piaskowy.urlshortenerbackend.user.model.dto.UserDto;
import com.piaskowy.urlshortenerbackend.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
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
