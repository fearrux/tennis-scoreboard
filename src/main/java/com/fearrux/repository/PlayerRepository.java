package com.fearrux.repository;

import com.fearrux.entity.Player;

import java.util.Optional;

public interface PlayerRepository extends Repository<Player, Long> {
    Optional<Player> findByName(String name);
}
