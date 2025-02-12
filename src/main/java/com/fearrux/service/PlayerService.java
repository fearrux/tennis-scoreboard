package com.fearrux.service;

import com.fearrux.entity.Player;
import com.fearrux.exception.InvalidNameException;
import com.fearrux.validator.PlayerNameValidator;
import com.fearrux.validator.ValidationResult;
import com.fearrux.validator.Validator;
import com.fearrux.repository.HibernatePlayerRepository;
import com.fearrux.repository.PlayerRepository;

import java.util.Optional;

public class PlayerService {
    private final PlayerRepository playerRepository = new HibernatePlayerRepository();
    private final Validator<String> nameValidator = new PlayerNameValidator();

    public Player findByName(String name) {
        ValidationResult result = nameValidator.validate(name);
        if (!result.isValid()) {
            throw new InvalidNameException(result.getErrors());
        }
        Optional<Player> maybePlayer = playerRepository.findByName(name);
        return maybePlayer.orElseGet(
                () -> save(Player.builder()
                        .name(name)
                        .build())
        );
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
