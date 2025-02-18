package com.fearrux.junit.service;

import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.entity.Player;
import com.fearrux.service.MatchScoreCalculationService;
import com.fearrux.state.MatchState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchScoreCalculationServiceTest {
    private Player player1;
    private Player player2;

    @BeforeEach
    void initializePlayers() {
        player1 = Player.builder()
                .id(1L)
                .build();
        player2 = Player.builder()
                .id(2L)
                .build();
    }

    @Test
    void gameDoesNotEndWhenPlayer1WinAtDeuce() {
        int currentNumberOfGames = 0;
        MatchState matchState = new MatchState(0, 0, currentNumberOfGames, currentNumberOfGames, 3, 3);

        MatchRequestDTO match = MatchRequestDTO.builder()
                .firstPlayer(player1)
                .secondPlayer(player2)
                .matchState(matchState)
                .build();
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

        Long winner = 1L;

        matchScoreCalculationService.updateScore(match, winner);
        Assertions.assertEquals(currentNumberOfGames, matchState.getFirstPlayerGames());
    }

    @Test
    void gameEndWhenPlayer1Leading3to3() {
        int nextNumberOfGames = 1;
        MatchState matchState = new MatchState(0, 0, 0, 0, 3, 0);

        MatchRequestDTO match = MatchRequestDTO.builder()
                .firstPlayer(player1)
                .secondPlayer(player2)
                .matchState(matchState)
                .build();
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

        Long winner = 1L;

        matchScoreCalculationService.updateScore(match, winner);
        Assertions.assertEquals(nextNumberOfGames, matchState.getFirstPlayerGames());
    }

    @Test
    void tieBreakStartWhenScoreIf6To6() {
        int gamesForTieBreak = 6;
        MatchState matchState = new MatchState(0, 0, gamesForTieBreak, gamesForTieBreak, 0, 0);

        MatchRequestDTO match = MatchRequestDTO.builder()
                .firstPlayer(player1)
                .secondPlayer(player2)
                .matchState(matchState)
                .build();
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

        matchScoreCalculationService.updateScore(match, null);

        Assertions.assertTrue(matchScoreCalculationService.isTieBreak(matchState.getFirstPlayerGames(), matchState.getSecondPlayerGames()));
    }

    @Test
    void scoreUpdateCorrectlyWhenPlayer1WinsAt2To2() {
        MatchState matchState = new MatchState(0, 0, 0, 0, 2, 2);

        MatchRequestDTO match = MatchRequestDTO.builder()
                .firstPlayer(player1)
                .secondPlayer(player2)
                .matchState(matchState)
                .build();
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

        Long winner = 1L;
        matchScoreCalculationService.updateScore(match, winner);

        Assertions.assertEquals(3, matchState.getFirstPlayerPoints());
        Assertions.assertEquals(2, matchState.getSecondPlayerPoints());
    }
}
