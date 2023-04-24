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

@Service
@Log4j2
public class WordService {

    private final WordRepository wordRepository;
    private final LanguageService languageService;

    public WordService(final WordRepository wordRepository, final LanguageService languageService) {
        this.wordRepository = wordRepository;
        this.languageService = languageService;
    }

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

    private void checkIfWordAlreadyExists(String word, Language language) {
        wordRepository.findAllByWordAndLanguage(word, language)
                .ifPresent(w -> {
                    log.warn("Word: " + word + " does not exists");
                    throw new WordAlreadyExists("Word: " + word + " does not exists");
                });
    }

    public WordDto getRandomWord(String languageCode) {
        Word word = wordRepository.getRandomWord(languageCode)
                .orElseThrow(() -> {
                    log.error("Something went wrong");
                    return new WordDoesNotExists("Something went wrong");
                });

        return new WordDto(word.getWord());
    }


}
