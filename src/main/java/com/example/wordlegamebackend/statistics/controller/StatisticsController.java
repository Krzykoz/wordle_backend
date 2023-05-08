/**
    This class represents the REST controller for the statistics API endpoints.
*/

package com.example.wordlegamebackend.statistics.controller;

import com.example.wordlegamebackend.statistics.model.Statistics;
import com.example.wordlegamebackend.statistics.service.StatisticsService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {

    /**
     * Constructor for StatisticsController.
     * @param statisticsService the statistics service to be used.
     */
    private final StatisticsService statisticsService;

    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * GET endpoint for getting the statistics for a specific word and language.
     * @param word the word to get the statistics for.
     * @param languageCode the language code to get the statistics for.
     * @param authentication the authentication object containing the user details.
     * @return the statistics for the specified word and language.
     */
    @GetMapping
    public Statistics get(@RequestParam String word, @RequestParam String languageCode, Authentication authentication) {
        return statisticsService.getStatistics(authentication, word, languageCode);
    }

}
