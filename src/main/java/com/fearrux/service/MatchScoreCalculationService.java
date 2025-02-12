package com.fearrux.service;

import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.exception.InvalidPlayerIdException;
import com.fearrux.state.MatchState;

import java.util.Objects;

public class MatchScoreCalculationService {
    public void updateScore(MatchRequestDTO match, Long playerId) {
        MatchState matchState = match.getMatchState();
        if (Objects.equals(match.getFirstPlayer().getId(), playerId)) {
            matchState.setFirstPlayerPoints(matchState.getFirstPlayerPoints() + 1);
        } else if (Objects.equals(match.getSecondPlayer().getId(), playerId)) {
            matchState.setSecondPlayerPoints(matchState.getSecondPlayerPoints() + 1);
        } else {
            throw new InvalidPlayerIdException("The player id is invalid.");
        }

    }

    private void checkPoints() {
    }
}
