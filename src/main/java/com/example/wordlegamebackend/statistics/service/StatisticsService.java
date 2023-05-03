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

@Service
public class StatisticsService {

    private final WordRepository wordRepository;
    private final GameRepository gameRepository;
    private final WordService wordService;

    public StatisticsService(final WordRepository wordRepository, final GameRepository gameRepository, final WordService wordService) {
        this.wordRepository = wordRepository;
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

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
