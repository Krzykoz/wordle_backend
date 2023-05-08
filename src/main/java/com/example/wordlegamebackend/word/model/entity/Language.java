package com.example.wordlegamebackend.word.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
  * Entity class representing a language. It is used to store and retrieve languages in the database.
  */
@Entity
@Table(name = "langugages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String language;
    private String languageCode;
}
