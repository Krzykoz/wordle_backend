package com.example.wordlegamebackend.user.model;

import com.example.wordlegamebackend.user.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Custom implementation of the {@link UserDetails} interface
 * that represents a user in the system.
 */
public record CustomUserDetails(User user) implements UserDetails {

    /**
     * Returns the authorities granted to the user. This method
     * always returns null as this project doesn't implement roles.
     *
     * @return the authorities granted to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Returns the password used to authenticate the user. This method
     * always returns null as this project doesn't store user passwords
     * in plain text.
     *
     * @return the password used to authenticate the user
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * Returns the email used to authenticate the user. This method
     * returns the email associated with the user's account.
     *
     * @return the email used to authenticate the user
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the user's account has expired. This method
     * always returns true as this project doesn't implement account
     * expiration.
     *
     * @return true if the user's account is valid (ie non-expired),
     * false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. This method
     * always returns true as this project doesn't implement account
     * locking.
     *
     * @return true if the user is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired. This
     * method always returns true as this project doesn't implement password
     * expiration.
     *
     * @return true if the user's credentials are valid (ie non-expired),
     * false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. This method
     * always returns true as this project doesn't implement account
     * disabling.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
