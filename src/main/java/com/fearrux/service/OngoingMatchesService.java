package com.fearrux.service;

import com.fearrux.dto.MatchRequestDTO;
import com.fearrux.exception.InvalidUuidException;
import com.fearrux.exception.MatchDoesNotExistException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private final Map<UUID, MatchRequestDTO> matches = new ConcurrentHashMap<>();

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    public void add(UUID uuid, MatchRequestDTO matchRequestDTO) throws InvalidUuidException {
        matches.put(uuid, matchRequestDTO);
    }

    public MatchRequestDTO get(UUID uuid) throws InvalidUuidException {
        MatchRequestDTO match = matches.get(uuid);
        if (match == null) {
            throw new MatchDoesNotExistException("The match not found.");
        }
        return match;
    }

    private OngoingMatchesService() {

    }
}
