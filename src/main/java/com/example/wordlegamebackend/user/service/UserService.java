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

@Service
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserModelMapper mapper;
    private final JwtService jwtService;


    UserService(final UserRepository userRepository, final UserModelMapper mapper, final JwtService jwtService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.jwtService = jwtService;
    }

    public UserDto getUserById(Long id) {
        return userRepository
                .findUserById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found"));
    }

    public UserDto getCurrentUser(Authentication authentication) {
        log.info("Getting current user: " + authentication.getPrincipal().toString());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.user();
        return mapper.toDto(user);
    }

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

    private String generateJwtTokenForUser(String email) {
        return userRepository
                .findByEmail(email)
                .map(u -> jwtService.generateToken(new CustomUserDetails(u)))
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));
    }

}
