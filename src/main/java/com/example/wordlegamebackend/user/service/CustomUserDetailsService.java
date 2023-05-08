package com.example.wordlegamebackend.user.service;


import com.example.wordlegamebackend.user.model.CustomUserDetails;
import com.example.wordlegamebackend.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service responsible for loading user details from the database.
 */

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details from the database using the provided email.
     *
     * @param email the email of the user to load
     * @return a UserDetails object representing the loaded user
     * @throws UsernameNotFoundException if a user with the provided email cannot be found
     */
    @Override
    public UserDetails loadUserByUsername(final String email) {
        log.info("Trying to load user with provided email: " + email);
        return userRepository.findByEmail(email)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> {
                    log.error("User with email: " + email + " not found");
                    return new UsernameNotFoundException("User with email: " + email + " not found");
                });
    }
}
