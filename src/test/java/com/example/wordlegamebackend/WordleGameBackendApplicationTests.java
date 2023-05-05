package com.example.wordlegamebackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles(profiles = "dev")
class WordleGameBackendApplicationTests {
    @Test
    void contextLoads() {
    }
}
