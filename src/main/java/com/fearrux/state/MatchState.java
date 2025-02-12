package com.fearrux.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MatchState {
    private int firstPlayerSets;
    private int secondPlayerSets;

    private int firstPlayerGames;
    private int secondPlayerGames;

    private int firstPlayerPoints;
    private int secondPlayerPoints;
}
