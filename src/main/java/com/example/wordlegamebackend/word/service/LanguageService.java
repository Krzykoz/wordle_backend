package com.example.wordlegamebackend.word.service;

import com.example.wordlegamebackend.word.exception.LanguageAlreadyExistsException;
import com.example.wordlegamebackend.word.exception.LanguageNotFoundException;
import com.example.wordlegamebackend.word.model.entity.Language;
import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.repository.LanguageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
            throw new LanguageAlreadyExistsException("That language already exists");
        }

        Language language = Language.builder()
                .language(addLanguageRequest.language())
                .languageCode(addLanguageRequest.languageCode())
                .build();

        languageRepository.save(language);
    }

    public Language getLanguageByCode(String languageCode) {
        return languageRepository.findLanguageByLanguageCode(languageCode)
                .orElseThrow(() -> new LanguageNotFoundException("Language with code: " + languageCode + " not found"));
    }

}
