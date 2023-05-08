/**
    This class represents a game entity in the system.
    The Game class is an entity class that contains the following data:
        id: the unique identifier of the game
        turns: the number of turns in the game
        guessed: a boolean indicating whether the word has been guessed
        word: the Word entity representing the word being guessed in the game
        user: the User entity representing the user playing the game
*/

package com.example.wordlegamebackend.game.model;

import com.example.wordlegamebackend.user.model.entity.User;
import com.example.wordlegamebackend.word.model.entity.Word;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private int turns;
    private boolean guessed;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
