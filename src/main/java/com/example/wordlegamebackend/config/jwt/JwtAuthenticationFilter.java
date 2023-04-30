package com.example.wordlegamebackend.config.jwt;

import com.example.wordlegamebackend.ErrorResponse;
import com.example.wordlegamebackend.user.model.CustomUserDetails;
import com.example.wordlegamebackend.user.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;


    public JwtAuthenticationFilter(final JwtService jwtService, final CustomUserDetailsService customUserDetailsService, final ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(final @NonNull HttpServletRequest request,
                                    final @NonNull HttpServletResponse response,
                                    final @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("Internal filter started");
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwtToken;
        final String email;
        try {
            jwtToken = authHeader.substring(7);
            email = jwtService.extractEmail(jwtToken);
        } catch (JwtException exception) {
            handleJwtException(response, exception, request);
            return;
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("Updating security context procedure started for email: " + email);
            CustomUserDetails userDetails = (CustomUserDetails) this.customUserDetailsService.loadUserByUsername(email);
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                log.info("Jwt token is valid, updating authentication context.");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleJwtException(HttpServletResponse response, JwtException exception, HttpServletRequest request) throws IOException {
        log.error("Jwt exception caught in JwtAuthenticationFilter due to " + exception.getMessage());
        String requestUri = request.getRequestURI();

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .path(requestUri)
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
