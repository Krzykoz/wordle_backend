package com.example.wordlegamebackend.word.repository;

import com.example.wordlegamebackend.word.model.entity.Language;
import com.example.wordlegamebackend.word.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    Optional<Word> findAllByWordAndLanguage(String word, Language language);

    @Query("SELECT w FROM Word w WHERE w.language.languageCode = ?1 ORDER BY random() LIMIT 1")
    Optional<Word> getRandomWord(String languageCode);

}
