package com.andrey.dotamanager.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getTeamWl() {
        return teamWl;
    }

    public void setTeamWl(int teamWl) {
        this.teamWl = teamWl;
    }

    public int getTeamSkill() {
        return teamSkill;
    }

    public void setTeamSkill(int teamSkill) {
        this.teamSkill = teamSkill;
    }

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
