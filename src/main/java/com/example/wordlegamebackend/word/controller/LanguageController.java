package com.example.wordlegamebackend.word.controller;

import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.service.LanguageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
