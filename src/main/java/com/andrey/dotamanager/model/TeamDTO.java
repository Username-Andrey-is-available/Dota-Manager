package com.andrey.dotamanager.model;

import lombok.Data;

@Data
public class TeamDTO {
    private Long id;
    private String name;
    private double budget;
    private String country;
    private int fans;
}