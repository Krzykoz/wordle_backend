/**
    This class represents the user statistics model for the WordLegameBackend.
*/

package com.example.wordlegamebackend.statistics.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserStatistics {
    long numberOfWins;
    double winsRatio;
}
