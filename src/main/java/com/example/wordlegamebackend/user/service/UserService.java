package com.piaskowy.urlshortenerbackend.user.service;

import com.piaskowy.urlshortenerbackend.user.model.CustomUserDetails;
import com.piaskowy.urlshortenerbackend.user.model.dto.UserDto;
import com.piaskowy.urlshortenerbackend.user.model.entity.User;
import com.piaskowy.urlshortenerbackend.user.model.mapper.UserModelMapper;
import com.piaskowy.urlshortenerbackend.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserModelMapper mapper;

    UserService(final UserRepository userRepository, final UserModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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

}
