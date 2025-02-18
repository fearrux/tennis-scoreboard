package com.fearrux.service;

import com.fearrux.dto.MatchDTO;
import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.entity.Match;
import com.fearrux.entity.Player;
import com.fearrux.repository.HibernateMatchRepository;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private final HibernateMatchRepository hibernateMatchRepository = new HibernateMatchRepository();

    public void save(MatchRequestDTO matchRequestDTO, Player winner) {
        hibernateMatchRepository.save(Match.builder()
                        .firstPlayer(matchRequestDTO.getFirstPlayer())
                        .secondPlayer(matchRequestDTO.getSecondPlayer())
                        .winner(winner)
                        .build());
    }

    public List<MatchDTO> findAll(int offset, int limit) {
        return hibernateMatchRepository.findAll(offset, limit).stream()
                .map(match -> MatchDTO.builder()
                        .firstPlayerName(match.getFirstPlayer().getName())
                        .secondPlayerName(match.getSecondPlayer().getName())
                        .winner(match.getWinner().getName())
                        .build())
                .toList();
    }

    public List<MatchDTO> findByName(String name, int offset, int limit) {
        return hibernateMatchRepository.findByName(name, offset, limit).stream()
                .map(match -> MatchDTO.builder()
                        .firstPlayerName(match.getFirstPlayer().getName())
                        .secondPlayerName(match.getSecondPlayer().getName())
                        .winner(match.getWinner().getName())
                        .build())
                .toList();
    }
}
