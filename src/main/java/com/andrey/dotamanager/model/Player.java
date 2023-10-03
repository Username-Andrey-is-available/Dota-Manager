package com.andrey.dotamanager.model;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Player() {

    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    private String name;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    private double salary;
    private double heroPickSkill;
    private double laningSkill;
    private boolean isCaptain;


    public boolean isCaptain() {
        return isCaptain;
    }

    public void setCaptain(boolean captain) {
        isCaptain = captain;
    }

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHeroPickSkill() {
        return heroPickSkill;
    }

    public void setHeroPickSkill(double heroPickSkill) {
        this.heroPickSkill = heroPickSkill;
    }

    public double getLaningSkill() {
        return laningSkill;
    }

    public void setLaningSkill(double laningSkill) {
        this.laningSkill = laningSkill;
    }

    public double getFarmSkill() {
        return farmSkill;
    }

    public void setFarmSkill(double farmSkill) {
        this.farmSkill = farmSkill;
    }

    public double getTeamfightSkill() {
        return teamfightSkill;
    }

    public void setTeamfightSkill(double teamfightSkill) {
        this.teamfightSkill = teamfightSkill;
    }

    public double getGreed() {
        return greed;
    }

    public void setGreed(double greed) {
        this.greed = greed;
    }

    public double getWardingSkill() {
        return wardingSkill;
    }

    public void setWardingSkill(double wardingSkill) {
        this.wardingSkill = wardingSkill;
    }

    public double getMicroSkill() {
        return microSkill;
    }

    public void setMicroSkill(double microSkill) {
        this.microSkill = microSkill;
    }

    public double getMacroSkill() {
        return macroSkill;
    }

    public void setMacroSkill(double macroSkill) {
        this.macroSkill = macroSkill;
    }

    public double getStressResistance() {
        return stressResistance;
    }

    public void setStressResistance(double stressResistance) {
        this.stressResistance = stressResistance;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getTeamwork() {
        return teamwork;
    }

    public void setTeamwork(double teamwork) {
        this.teamwork = teamwork;
    }

    public int getWinLossDelta() {
        return winLossDelta;
    }

    public void setWinLossDelta(int winLossDelta) {
        this.winLossDelta = winLossDelta;
    }

    public double getSkill() {
        return skill;
    }

    public void setSkill(double skill) {
        this.skill = skill;
    }

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double calculateSkill(double heroPickSkill, double laningSkill, double farmSkill,
                                 double TeamfightSkill, double wardingSkill, double microSkill, double macroSkill) {
        return (heroPickSkill + laningSkill + farmSkill + teamfightSkill + wardingSkill + microSkill) / 12
                + macroSkill / 2;
    }


}
