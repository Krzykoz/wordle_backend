package com.example.wordlegamebackend.statistics.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Statistics {
    WordStatistics wordStatistics;
    UserStatistics userStatistics;
}
