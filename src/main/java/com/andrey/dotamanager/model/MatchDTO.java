package com.andrey.dotamanager.model;

import lombok.Data;

@Data
public class MatchDTO {
    private Long id;
    private String team1;
    private String team2;
    private String winner;
}
