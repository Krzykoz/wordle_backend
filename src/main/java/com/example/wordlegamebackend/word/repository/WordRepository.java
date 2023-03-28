package com.example.wordlegamebackend.word.repository;

import com.example.wordlegamebackend.word.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
