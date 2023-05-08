package com.example.wordlegamebackend.word.service;

import com.example.wordlegamebackend.word.exception.LanguageAlreadyExistsException;
import com.example.wordlegamebackend.word.exception.LanguageNotFoundException;
import com.example.wordlegamebackend.word.model.entity.Language;
import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.repository.LanguageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *The LanguageService class provides methods to access and manipulate Language objects
 */
@Service
@Log4j2
public class LanguageService {

    private final LanguageRepository languageRepository;

    /**
     * Constructs a LanguageService with the given LanguageRepository
     *
     * @param languageRepository The LanguageRepository to use
     */
    public LanguageService(final LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    /**
     * Returns all languages stored in the LanguageRepository
     *
     * @return A list of all languages
     */
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    /**
     * Adds a new language to the LanguageRepository
     *
     * @param addLanguageRequest The request containing the language name and code
     * @throws LanguageAlreadyExistsException If the language with the specified code already exists
     */
    public void addNewLanguage(AddLanguageRequest addLanguageRequest) {
        boolean doesLanguageExists = languageRepository.existsLanguageByLanguageCode(addLanguageRequest.languageCode());

        if (doesLanguageExists) {
            log.error("Language with code: " + addLanguageRequest.languageCode() + " already exists");
            throw new LanguageAlreadyExistsException("That language already exists");
        }

        Language language = Language.builder()
                .language(addLanguageRequest.language())
                .languageCode(addLanguageRequest.languageCode())
                .build();

        languageRepository.save(language);
    }

    /**
     * Returns the Language with the specified language code
     *
     * @param languageCode The code of the language to find
     * @return The Language object with the specified code
     * @throws LanguageNotFoundException If no Language object with the specified code is found
     */
    public Language getLanguageByCode(String languageCode) {
        return languageRepository.findLanguageByLanguageCode(languageCode)
                .orElseThrow(() -> new LanguageNotFoundException("Language with code: " + languageCode + " not found"));
    }

    /**
     * Removes the Language object with the specified id from the LanguageRepository
     *
     * @param id The id of the Language object to remove
     */
    public void removeLanguageById(Long id) {
        languageRepository.deleteById(id);
    }
}
