package com.example.wordlegamebackend.word.controller;

import com.example.wordlegamebackend.word.model.entity.Language;
import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing languages
 */
@RestController
@RequestMapping("api/v1/language")
public class LanguageController {

    private final LanguageService languageService;

    /**
     * Constructs the LanguageController instance with the given LanguageService instance
     * @param languageService the LanguageService instance to be used for language management
     */
    public LanguageController(final LanguageService languageService) {
        this.languageService = languageService;
    }

    /**
     * Adds a new language with the given AddLanguageRequest object.
     * @param addLanguageRequest the request object that includes the language name and code
     */
    @PostMapping
    void addLanguage(@RequestBody AddLanguageRequest addLanguageRequest) {
        languageService.addNewLanguage(addLanguageRequest);
    }

    /**
     * Returns a list of all languages in the system
     * @return a list of all languages in the system
     */
    @GetMapping
    public List<Language> all() {
        return languageService.getAllLanguages();
    }

    /**
     * Removes a language with the given id
     * @param id the id of the language to be removed
     */
    @DeleteMapping("{id}")
    void removeLanguage(@PathVariable final Long id) {
        languageService.removeLanguageById(id);
    }
}
