package com.example.wordlegamebackend.word.service;

import com.example.wordlegamebackend.word.exception.WordAlreadyExists;
import com.example.wordlegamebackend.word.exception.WordDoesNotExists;
import com.example.wordlegamebackend.word.model.entity.Language;
import com.example.wordlegamebackend.word.model.entity.Word;
import com.example.wordlegamebackend.word.model.entity.dto.WordDto;
import com.example.wordlegamebackend.word.model.request.AddWordRequest;
import com.example.wordlegamebackend.word.repository.WordRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides services related to words, including adding new words, finding words by word and language,
 * getting a random word, and getting a list of all words.
 */
@Service
@Log4j2
public class WordService {

    private final WordRepository wordRepository;
    private final LanguageService languageService;

    /**
     * Creates a new instance of WordService.
     *
     * @param wordRepository The repository to be used to access Word entities
     * @param languageService The LanguageService to be used to access Language entities
     */
    public WordService(final WordRepository wordRepository, final LanguageService languageService) {
        this.wordRepository = wordRepository;
        this.languageService = languageService;
    }

    /**
     * Adds a new word to the system.
     *
     * @param addWordRequest The AddWordRequest containing the details of the word to be added
     * @throws WordAlreadyExists If the word already exists in the system
     */
    @Transactional
    public void addNewWord(AddWordRequest addWordRequest) {

        Language language = languageService.getLanguageByCode(addWordRequest.languageCode());

        checkIfWordAlreadyExists(addWordRequest.word(), language);

        Word word = Word.builder()
                .language(language)
                .word(addWordRequest.word())
                .build();

        wordRepository.save(word);
    }

    /**
     * Checks if a given word already exists for a given language.
     *
     * @param word The word to check
     * @param language The language to check the word for
     * @throws WordAlreadyExists If the word already exists for the given language
     */
    private void checkIfWordAlreadyExists(String word, Language language) {
        wordRepository.findAllByWordAndLanguage(word, language)
                .ifPresent(w -> {
                    log.warn("Word: " + word + " does not exists");
                    throw new WordAlreadyExists("Word: " + word + " does not exists");
                });
    }

    /**
     * Finds a word by its value and the code of its language.
     *
     * @param word The value of the word to find
     * @param languageCode The code of the language to find the word for
     * @return The Word entity representing the word, if found
     * @throws RuntimeException If no word is found for the given values
     */
    public Word findWordByWordAndLanguage(String word, String languageCode) {
        Language language = languageService.getLanguageByCode(languageCode);
        return wordRepository.findAllByWordAndLanguage(word, language).orElseThrow(RuntimeException::new);
    }

    /**
     * Gets a random word for a given language.
     *
     * @param languageCode The code of the language to get a random word for
     * @return The WordDto representing the random word
     * @throws WordDoesNotExists If no words are found for the given language
     */
    public WordDto getRandomWord(String languageCode) {
        Word word = wordRepository.getRandomWord(languageCode)
                .orElseThrow(() -> {
                    log.error("Something went wrong");
                    return new WordDoesNotExists("Something went wrong");
                });

        return new WordDto(word.getWord());
    }

    /**
     *Gets a list of all words in the system.
     * @return The List of Word entities representing all words in the system
     */
    public List<Word> getAll() {
        return wordRepository.findAll();
    }

}
