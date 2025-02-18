package com.fearrux.service;

import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.entity.Player;
import com.fearrux.state.MatchState;

import java.util.Objects;

public class MatchScoreCalculationService {
    private static final int MINIMUM_POINTS_NUMBER_FOR_GAME = 4;
    private static final int MINIMUM_GAMES_NUMBER_TO_SET = 6;
    private static final int MINIMUM_GAMES_NUMBER_TO_SET_IN_TIE_BREAK = 7;
    private static final int MINIMUM_DIFFERENCE_BETWEEN_PLAYERS = 2;
    private static final int NUMBER_OF_SETS_TO_WIN = 3;
    private static final int GAMES_NUMBER_IN_TIE_BREAK = 6;

    public void updateScore(MatchRequestDTO match, Long playerId) {
        MatchState matchState = match.getMatchState();
        if (isTieBreak(matchState.getFirstPlayerGames(), matchState.getSecondPlayerGames())) {
            handleTieBreak(match, playerId);
            return;
        }

        updatePoints(match, playerId);
        updateGames(match, playerId);
        updateSets(match, playerId);
    }

    private void updatePoints(MatchRequestDTO match, Long playerId) {
        MatchState matchState = match.getMatchState();
        boolean isFirstPlayer = Objects.equals(match.getFirstPlayer().getId(), playerId);
        if (isFirstPlayer) {
            matchState.setFirstPlayerPoints(matchState.getFirstPlayerPoints() + 1);
        } else {
            matchState.setSecondPlayerPoints(matchState.getSecondPlayerPoints() + 1);
        }
    }

    private void updateGames(MatchRequestDTO match, Long playerId) {
        MatchState matchState = match.getMatchState();
        boolean isFirstPlayer = Objects.equals(match.getFirstPlayer().getId(), playerId);
        int winnerPoints = isFirstPlayer ? matchState.getFirstPlayerPoints() : matchState.getSecondPlayerPoints();
        int loserPoints = isFirstPlayer ? matchState.getSecondPlayerPoints() : matchState.getFirstPlayerPoints();
        if (isFirstPlayer) {
            if (isGame(winnerPoints, loserPoints)) {
                resetPoints(matchState);
                matchState.setFirstPlayerGames(matchState.getFirstPlayerGames() + 1);
            }
        } else {
            if (isGame(winnerPoints, loserPoints)) {
                resetPoints(matchState);
                matchState.setSecondPlayerGames(matchState.getSecondPlayerGames() + 1);
            }
        }
    }

    private void updateSets(MatchRequestDTO match, Long playerId) {
        MatchState matchState = match.getMatchState();
        boolean isFirstPlayer = Objects.equals(match.getFirstPlayer().getId(), playerId);
        int winnerGames = isFirstPlayer ? matchState.getFirstPlayerGames() : matchState.getSecondPlayerGames();
        int loserGames = isFirstPlayer ? matchState.getSecondPlayerGames() : matchState.getFirstPlayerGames();

        if (isFirstPlayer) {
            if (isSet(winnerGames, loserGames)) {
                resetGames(matchState);
                resetPoints(matchState);
                matchState.setFirstPlayerSets(matchState.getFirstPlayerSets() + 1);
            }
        } else {
            if (isSet(winnerGames, loserGames)) {
                resetGames(matchState);
                resetPoints(matchState);
                matchState.setSecondPlayerSets(matchState.getSecondPlayerSets() + 1);
            }
        }
    }
    private void handleTieBreak(MatchRequestDTO match, Long playerId) {
        MatchState matchState = match.getMatchState();
        boolean isFirstPlayer = Objects.equals(match.getFirstPlayer().getId(), playerId);
        if (isFirstPlayer) {
            if (matchState.getFirstPlayerPoints() + 1 >= MINIMUM_GAMES_NUMBER_TO_SET_IN_TIE_BREAK &&
                matchState.getFirstPlayerPoints() - matchState.getSecondPlayerPoints() >= MINIMUM_DIFFERENCE_BETWEEN_PLAYERS) {
                resetPoints(matchState);
                resetGames(matchState);
                matchState.setFirstPlayerSets(matchState.getFirstPlayerSets() + 1);
                return;
            }
        } else {
            if (matchState.getSecondPlayerPoints() + 1 >= MINIMUM_GAMES_NUMBER_TO_SET_IN_TIE_BREAK &&
                    matchState.getSecondPlayerPoints() - matchState.getFirstPlayerPoints() >= MINIMUM_DIFFERENCE_BETWEEN_PLAYERS) {
                resetGames(matchState);
                resetPoints(matchState);
                matchState.setSecondPlayerSets(matchState.getSecondPlayerSets() + 1);
                return;
            }
        }
        updatePoints(match, playerId);
    }

    private boolean isGame(int winnerPoints, int loserPoints) {
        return winnerPoints >= MINIMUM_POINTS_NUMBER_FOR_GAME && winnerPoints - loserPoints >= MINIMUM_DIFFERENCE_BETWEEN_PLAYERS;
    }

    private boolean isSet(int winnerPoints, int loserPoints) {
        return winnerPoints >= MINIMUM_GAMES_NUMBER_TO_SET && winnerPoints - loserPoints >= MINIMUM_DIFFERENCE_BETWEEN_PLAYERS;
    }

    private void resetPoints(MatchState matchState) {
        matchState.setFirstPlayerPoints(0);
        matchState.setSecondPlayerPoints(0);
    }

    private void resetGames(MatchState matchState) {
        matchState.setFirstPlayerGames(0);
        matchState.setSecondPlayerGames(0);
    }

    public boolean isTieBreak(int firstPlayerGames, int secondPlayerGames) {
        return firstPlayerGames == GAMES_NUMBER_IN_TIE_BREAK && firstPlayerGames == secondPlayerGames;
    }

    public boolean isMatchOver(MatchState matchState) {
        return matchState.getFirstPlayerSets() == NUMBER_OF_SETS_TO_WIN || matchState.getSecondPlayerSets() == NUMBER_OF_SETS_TO_WIN;
    }

    public Player getWinner(MatchRequestDTO matchRequestDTO) {
        MatchState matchState = matchRequestDTO.getMatchState();
        return matchState.getFirstPlayerSets() == NUMBER_OF_SETS_TO_WIN ? matchRequestDTO.getFirstPlayer() : matchRequestDTO.getSecondPlayer();
    }
}
