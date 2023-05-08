package com.example.wordlegamebackend.word.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * The Word class represents a word entity in the database.
 * It is mapped to the "words" table in the database.
 */
@Entity
@Table(name = "words")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String word;
    @ManyToOne
    @JoinColumn(name = "language_id")
    @NotNull
    private Language language;
}
