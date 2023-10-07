package com.andrey.dotamanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double budget;
    private String country;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    private int fans;
    private int teamWl;
    private int teamSkill;
    public double calculateTeamWork(List<Player> players){
        double allTW = 0;
        for(Player player : players){
            allTW += player.getTeamwork();
        }
        return allTW;
    }

    public double calculateStressResistance(List<Player> players){
        double allSR = 0;
        for(Player player : players){
            allSR += player.getStressResistance();
        }
        return allSR;
    }
}
