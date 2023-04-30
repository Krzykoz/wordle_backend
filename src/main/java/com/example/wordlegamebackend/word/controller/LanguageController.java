package com.example.wordlegamebackend.word.controller;

import com.example.wordlegamebackend.word.model.entity.Language;
import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/language")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    void addLanguage(@RequestBody AddLanguageRequest addLanguageRequest) {
        languageService.addNewLanguage(addLanguageRequest);
    }

    @GetMapping
    public List<Language> all() {
        return languageService.getAllLanguages();
    }

    @DeleteMapping("{id}")
    void removeLanguage(@PathVariable final Long id) {
        languageService.removeLanguageById(id);
    }
}
