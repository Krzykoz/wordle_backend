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

    private final StatisticsService statisticsService;

    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public Statistics get(@RequestParam String word, @RequestParam String languageCode, Authentication authentication) {
        return statisticsService.getStatistics(authentication, word, languageCode);
    }

}
