package com.example.wordlegamebackend.user.service;

import com.example.wordlegamebackend.config.jwt.JwtService;
import com.example.wordlegamebackend.user.exception.UserAlreadyExistsException;
import com.example.wordlegamebackend.user.model.CustomUserDetails;
import com.example.wordlegamebackend.user.model.dto.UserDto;
import com.example.wordlegamebackend.user.model.entity.User;
import com.example.wordlegamebackend.user.model.mapper.UserModelMapper;
import com.example.wordlegamebackend.user.model.request.RegisterRequest;
import com.example.wordlegamebackend.user.model.response.RegisterResponse;
import com.example.wordlegamebackend.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for user related operations
 */
@Service
@Log4j2
public class UserService {
    /**
     * The user repository for database related operations
     */
    private final UserRepository userRepository;

    /**
     * The user model mapper for entity-dto mapping
     */
    private final UserModelMapper mapper;

    /**
     * The jwt service for token related operations
     */
    private final JwtService jwtService;


    UserService(final UserRepository userRepository, final UserModelMapper mapper, final JwtService jwtService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.jwtService = jwtService;
    }

    /**
     * Gets a user with the provided id
     *
     * @param id The id of the user to be fetched
     * @return UserDto object representing the user with the given id
     * @throws UsernameNotFoundException if no user is found with the given id
     */
    public UserDto getUserById(Long id) {
        return userRepository
                .findUserById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found"));
    }

    /**
     * Gets the currently authenticated user
     *
     * @param authentication The authentication object containing the currently authenticated user's details
     * @return UserDto object representing the currently authenticated user
     */
    public UserDto getCurrentUser(Authentication authentication) {
        log.info("Getting current user: " + authentication.getPrincipal().toString());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.user();
        return mapper.toDto(user);
    }

    /**
     * Registers a new user
     *
     * @param registerRequest The request object containing the user's details to be registered
     * @return The RegisterResponse object containing the registered user's details and the jwt token
     * @throws UserAlreadyExistsException if a user already exists with the provided email address
     * @throws UsernameNotFoundException if no user is found with the provided email address
     */
    public RegisterResponse signUpNewUser(RegisterRequest registerRequest) {
        log.info("Checking if user with email " + registerRequest.email() + " already exists");
        userRepository.findByEmail(registerRequest.email())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("User with email: " + registerRequest.email() + " already exists");
                });

        User user = User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .email(registerRequest.email())
                .build();

        log.info("Saving new user " + user + " already exists");
        User registeredUser = userRepository.save(user);

        String token = generateJwtTokenForUser(registerRequest.email());

        return new RegisterResponse(registeredUser, token);
    }

    /**
     * Generates a jwt token for the user with the provided email
     */
    private String generateJwtTokenForUser(String email) {
        return userRepository
                .findByEmail(email)
                .map(u -> jwtService.generateToken(new CustomUserDetails(u)))
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));
    }
}
