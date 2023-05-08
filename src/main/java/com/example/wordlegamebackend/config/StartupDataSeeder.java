package com.example.wordlegamebackend.config;

import com.example.wordlegamebackend.word.model.request.AddLanguageRequest;
import com.example.wordlegamebackend.word.model.request.AddWordRequest;
import com.example.wordlegamebackend.word.service.LanguageService;
import com.example.wordlegamebackend.word.service.WordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupDataSeeder implements CommandLineRunner {
    private final WordService wordService;
    private final LanguageService languageService;

    public StartupDataSeeder(final WordService wordService, final LanguageService languageService) {
        this.wordService = wordService;
        this.languageService = languageService;
    }

    @Override
    public void run(final String... args) throws Exception {
        AddLanguageRequest addLanguageRequest = new AddLanguageRequest("Polski", "PL");
        languageService.addNewLanguage(addLanguageRequest);

        AddWordRequest addWordRequest = new AddWordRequest("wyraz", "PL");
        AddWordRequest addWordRequest2 = new AddWordRequest("nudny", "PL");
        wordService.addNewWord(addWordRequest);
        wordService.addNewWord(addWordRequest2);
    }
}
