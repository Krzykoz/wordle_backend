package com.example.wordlegamebackend.word.controller;

import com.example.wordlegamebackend.word.model.entity.Word;
import com.example.wordlegamebackend.word.model.entity.dto.WordDto;
import com.example.wordlegamebackend.word.model.request.AddWordRequest;
import com.example.wordlegamebackend.word.service.WordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/word")

public class WordController {

    private final WordService wordService;

    public WordController(final WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/random")
    public WordDto getRandomWord(@RequestParam String languageCode) {
        return wordService.getRandomWord(languageCode);
    }

    @GetMapping
    public List<Word> all() {
        return wordService.getAll();
    }

    @PostMapping
    private void addNewWord(@RequestBody AddWordRequest addWordRequest) {
        wordService.addNewWord(addWordRequest);
    }

}
