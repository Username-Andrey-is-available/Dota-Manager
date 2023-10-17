package com.andrey.dotamanager.model;

import lombok.Data;

@Data
public class PlayerDTO {
    private Long id;
    private PlayerRole playerRole;
    private String name;
    private double salary;
    private double heroPickSkill;
    private double laningSkill;
    private boolean isCaptain;
    private double farmSkill;
    private double teamfightSkill;
    private double greed;
    private double wardingSkill;
    private double microSkill;
    private double macroSkill;
    private double stressResistance;
    private double cost;
    private String nickname;
    private double teamwork;
    private int winLossDelta;
    private double skill;
}
