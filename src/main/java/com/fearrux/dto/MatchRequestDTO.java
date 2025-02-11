package com.fearrux.dto;

import com.fearrux.entity.Player;
import com.fearrux.state.MatchState;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class MatchRequestDTO {
    private final UUID uuid = UUID.randomUUID();
    private Player firstPlayer;
    private Player secondPlayer;
    @Builder.Default
    private MatchState matchState = new MatchState(0, 0, 0, 0, 0, 0);
}