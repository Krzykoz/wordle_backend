/**
    This class represents the word statistics model for the WordLegameBackend.
*/

package com.example.wordlegamebackend.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WordStatistics {
    double wordWinRatio;
    double averageTurnsInWonGames;
    double averageTurnsInAllGames;
}
