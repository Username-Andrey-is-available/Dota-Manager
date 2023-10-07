package com.andrey.dotamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

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

    public Player() {

    }

    public Player(Long id, Role role, String name, Team team, double salary, double heroPickSkill,
                  double laningSkill, boolean isCaptain, double farmSkill, double teamfightSkill,
                  double greed, double wardingSkill, double microSkill, double macroSkill,
                  double stressResistance, double cost, String nickname, double teamwork, int winLossDelta) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.team = team;
        this.salary = salary;
        this.heroPickSkill = heroPickSkill;
        this.laningSkill = laningSkill;
        this.isCaptain = isCaptain;
        this.farmSkill = farmSkill;
        this.teamfightSkill = teamfightSkill;
        this.greed = greed;
        this.wardingSkill = wardingSkill;
        this.microSkill = microSkill;
        this.macroSkill = macroSkill;
        this.stressResistance = stressResistance;
        this.cost = cost;
        this.nickname = nickname;
        this.teamwork = teamwork;
        this.winLossDelta = winLossDelta;
        this.skill = calculateSkill(this.heroPickSkill, this.laningSkill, this.farmSkill,
                this.getTeamfightSkill(), this.wardingSkill, this.microSkill, this.macroSkill);
    }



    public double calculateSkill(double heroPickSkill, double laningSkill, double farmSkill,
                                 double TeamfightSkill, double wardingSkill, double microSkill, double macroSkill) {
        return (heroPickSkill + laningSkill + farmSkill + teamfightSkill + wardingSkill + microSkill) / 12
                + macroSkill / 2;
    }


}
