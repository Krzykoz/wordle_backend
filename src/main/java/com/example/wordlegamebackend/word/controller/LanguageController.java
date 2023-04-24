package com.example.wordlegamebackend.word.controller;

import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.service.LanguageService;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("{id}")
    void removeLanguage(@PathVariable final Long id) {
        languageService.removeLanguageById(id);
    }
}
