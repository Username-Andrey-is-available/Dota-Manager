package com.andrey.dotamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    private PlayerRole playerRole;

    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
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


    public double calculateSkill(double heroPickSkill, double laningSkill, double farmSkill,
                                 double TeamfightSkill, double wardingSkill, double microSkill, double macroSkill) {
        return (heroPickSkill + laningSkill + farmSkill + teamfightSkill + wardingSkill + microSkill) / 12
                + macroSkill / 2;
    }


}
