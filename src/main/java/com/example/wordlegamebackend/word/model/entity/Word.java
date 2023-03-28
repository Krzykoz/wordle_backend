package com.example.wordlegamebackend.word.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String word;

}
