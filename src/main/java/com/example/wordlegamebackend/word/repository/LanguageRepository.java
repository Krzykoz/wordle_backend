package com.example.wordlegamebackend.word.repository;

import com.example.wordlegamebackend.word.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findLanguageByLanguageCode(String languageCode);

    boolean existsLanguageByLanguageCode(String languageCode);
}
