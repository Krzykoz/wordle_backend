package com.example.wordlegamebackend.word.controller;

import com.example.wordlegamebackend.word.model.entity.Word;
import com.example.wordlegamebackend.word.model.entity.dto.WordDto;
import com.example.wordlegamebackend.word.model.request.AddWordRequest;
import com.example.wordlegamebackend.word.service.WordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The WordController class handles HTTP requests related to Word entity.
 */
@RestController
@RequestMapping("api/v1/word")

public class WordController {

    private final WordService wordService;

    /**
     * Constructs a new WordController with the specified WordService.
     * @param wordService the WordService object
     */
    public WordController(final WordService wordService) {
        this.wordService = wordService;
    }

    /**
     * Returns a random word in the specified language.
     * @param languageCode the language code of the requested word
     * @return the WordDto object containing the requested word
     */
    @GetMapping("/random")
    public WordDto getRandomWord(@RequestParam String languageCode) {
        return wordService.getRandomWord(languageCode);
    }

    /**
     * Returns a list of all words.
     * @return a List of all Word objects
     */
    @GetMapping
    public List<Word> all() {
        return wordService.getAll();
    }

    /**
     * Adds a new word to the database.
     * @param addWordRequest the AddWordRequest object containing information for the new word
     */
    @PostMapping
    private void addNewWord(@RequestBody AddWordRequest addWordRequest) {
        wordService.addNewWord(addWordRequest);
    }
}
