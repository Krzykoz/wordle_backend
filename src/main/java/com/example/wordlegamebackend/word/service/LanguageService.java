package com.example.wordlegamebackend.word.service;

import com.example.wordlegamebackend.word.exception.NotFoundException;
import com.example.wordlegamebackend.word.model.entity.Language;
import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.repository.LanguageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(final LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public void addNewLanguage(AddLanguageRequest addLanguageRequest) {
        boolean doesLanguageExists = languageRepository.existsLanguageByLanguageCode(addLanguageRequest.languageCode());

        if (doesLanguageExists){
            log.error("Language with code: " + addLanguageRequest.languageCode() + " already exists");
            throw new RuntimeException("That language already exists");
        }

        Language language = Language.builder()
                .language(addLanguageRequest.language())
                .languageCode(addLanguageRequest.languageCode())
                .build();

        languageRepository.save(language);
    }

}
