package com.fearrux.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MatchDTO {
    private String firstPlayerName;
    private String secondPlayerName;
    private String winner;
}
