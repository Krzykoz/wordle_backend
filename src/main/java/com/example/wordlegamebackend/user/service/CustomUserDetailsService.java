package com.piaskowy.urlshortenerbackend.user.service;

import com.piaskowy.urlshortenerbackend.user.model.CustomUserDetails;
import com.piaskowy.urlshortenerbackend.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        log.info("Trying to load user with provided email: " + email);
        return userRepository.findByEmail(email)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> {
                    log.error("User with email: " + email + " not found");
                    throw new UsernameNotFoundException("User with email: " + email + " not found");
                });
    }
}
