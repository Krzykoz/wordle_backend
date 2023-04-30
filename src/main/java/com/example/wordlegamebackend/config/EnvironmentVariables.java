package com.example.wordlegamebackend.config;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Primary
@Component
public class EnvironmentVariables {
    @Value("${config.jwt.secret-key}")
    private String jwtSecretKey;
}
