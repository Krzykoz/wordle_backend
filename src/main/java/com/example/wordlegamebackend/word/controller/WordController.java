package com.example.wordlegamebackend.word.controller;

import com.example.wordlegamebackend.word.model.entity.dto.WordDto;
import com.example.wordlegamebackend.word.model.request.AddWordRequest;
import com.example.wordlegamebackend.word.service.WordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/word")

public class WordController {

    private final WordService wordService;

    public WordController(final WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public WordDto getRandomWord(@RequestParam String languageCode) {
        return wordService.getRandomWord(languageCode);
    }

    @PostMapping
    private void addNewWord(@RequestBody AddWordRequest addWordRequest) {
        wordService.addNewWord(addWordRequest);
    }

}
