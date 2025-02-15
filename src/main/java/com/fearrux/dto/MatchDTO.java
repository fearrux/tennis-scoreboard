package com.fearrux.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MatchDTO {
    private String firstPlayerName;
    private String secondPlayerName;
    private String winner;
}
