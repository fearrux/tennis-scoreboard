package com.fearrux.repository;

import com.fearrux.entity.Match;

import java.util.List;

public interface MatchRepository extends Repository<Match, Long> {

    List<Match> findByName(String name, int offset, int limit);

    List<Match> findAll(int offset, int limit);
}
