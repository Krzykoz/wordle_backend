package com.example.wordlegamebackend.game.model;

import com.example.wordlegamebackend.user.model.entity.User;
import com.example.wordlegamebackend.word.model.entity.Word;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
