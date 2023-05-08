package com.example.wordlegamebackend.statistics.service;

import com.example.wordlegamebackend.game.model.Game;
import com.example.wordlegamebackend.game.repository.GameRepository;
import com.example.wordlegamebackend.statistics.model.Statistics;
import com.example.wordlegamebackend.statistics.model.UserStatistics;
import com.example.wordlegamebackend.statistics.model.WordStatistics;
import com.example.wordlegamebackend.user.model.CustomUserDetails;
import com.example.wordlegamebackend.user.model.entity.User;
import com.example.wordlegamebackend.word.model.entity.Word;
import com.example.wordlegamebackend.word.repository.WordRepository;
import com.example.wordlegamebackend.word.service.WordService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service class for generating game statistics.
 */
@Service
public class StatisticsService {
    private final GameRepository gameRepository;
    private final WordService wordService;

    public StatisticsService(final GameRepository gameRepository, final WordService wordService) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

    /**
     * Generates statistics for a given word in a given language and an optional user.
     *
     * @param authentication the authentication of the user. If null, only the word statistics are returned.
     * @param word the word for which statistics are to be generated.
     * @param languageCode the language code of the word.
     * @return statistics for the word and optionally, for the user as well.
     */
    public Statistics getStatistics(Authentication authentication, String word, String languageCode) {
        WordStatistics wordStatistics = getWordStatistics(word, languageCode);
        if (authentication == null) {
            return Statistics.builder()
                    .wordStatistics(wordStatistics)
                    .build();
        }

        return Statistics.builder()
                .userStatistics(getUserStatistics(authentication))
                .wordStatistics(wordStatistics).build();
    }

    /**
     * Generates statistics for a given user.
     *
     * @param authentication the authentication of the user.
     * @return statistics for the user.
     */
    private UserStatistics getUserStatistics(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.user();

        List<Game> games = gameRepository.findAllByUserId(user.getId()).orElse(Collections.emptyList());

        long totalNumberOfGames = games.size();

        long numberOfGuessedWords = games
                .stream()
                .filter(Game::isGuessed)
                .count();

        double winsRatio = totalNumberOfGames > 0 ? ((double) numberOfGuessedWords / totalNumberOfGames) * 100 : 0;


        return new UserStatistics(numberOfGuessedWords, winsRatio);
    }

    /**
     * Generates statistics for a given word.
     *
     * @param word the word for which statistics are to be generated.
     * @param languageCode the language code of the word.
     * @return statistics for the word.
     */
    private WordStatistics getWordStatistics(String word, String languageCode) {
        Word guessingWord = wordService.findWordByWordAndLanguage(word, languageCode);

        List<Game> games = gameRepository.findAllByWord(guessingWord).orElse(Collections.emptyList());
        long totalNumberOfGames = games.size();
        long numberOfGuessedWords = games
                .stream()
                .filter(Game::isGuessed)
                .count();

        double winsRatio = totalNumberOfGames > 0 ? ((double) numberOfGuessedWords / totalNumberOfGames) * 100 : 0;

        double averageTurnsInWonGames = games.stream()
                .filter(Game::isGuessed)
                .mapToInt(Game::getTurns)
                .average()
                .orElse(0);

        double averageTurnsInAllGames = games.stream()
                .mapToInt(Game::getTurns)
                .average()
                .orElse(0);

        return new WordStatistics(winsRatio, averageTurnsInWonGames, averageTurnsInAllGames);
    }

}
