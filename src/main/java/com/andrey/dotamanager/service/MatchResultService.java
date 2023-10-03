package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Role;
import com.andrey.dotamanager.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MatchResultService {
    private Random random;
    @PostConstruct
    public void init() {
        random = new Random();
    }

    private double pickStage;
    private double easylane;
    private double hardlane;
    private double midlane;
    private double roshan;
    private double towerDef;
    private double towerPush;
    private double earlyGame;
    private double midGame;
    private double lateGame;
    private double chanceToOverdraft;
    private double runes;
    private double fastPush;
    private double chanceToPickBetter;
    private double supportsMicroActions;
    private double coresMicroActions;
    private double perfectExecution;
    private double randomNonsense;

    protected double calculatePickStage(List<Player> players, double chanceToOverdraft, double chanceToPickBetter){
        Player midlaner = new Player();
        Player capitan = new Player();
        for(Player player : players){
            if(player.getRole() == Role.MIDER)midlaner = player;
            if(player.isCaptain())capitan = player;
        }
        this.pickStage =  (capitan.getHeroPickSkill() + midlaner.getHeroPickSkill()) *
                Math.max(chanceToOverdraft, chanceToPickBetter);
        return this.pickStage;
    }

    protected double calculateEasylane(List<Player> players){
        Player carry = new Player();
        Player hardsupport = new Player();
        for(Player player : players){
            if(player.getRole() == Role.CARRY)carry = player;
            if(player.getRole() == Role.HARDSUPPORT)hardsupport = player;
        }
        this.easylane = carry.getLaningSkill() + (carry.getMicroSkill() + hardsupport.getMicroSkill()) / 10 +
                hardsupport.getLaningSkill() + pickStage / 10;
        return this.easylane;
    }

    protected double calculateHardlane(List<Player> players){
        Player hard = new Player();
        Player support = new Player();
        for(Player player : players){
            if(player.getRole() == Role.OFFLANER)hard = player;
            if(player.getRole() == Role.SUPPORT)support = player;
        }
        this.hardlane = hard.getLaningSkill() + (hard.getMicroSkill() + support.getMicroSkill()) / 10 +
                support.getLaningSkill() + pickStage / 2;
        return this.hardlane;
    }

    protected double calculateMidlane(List<Player> players){
        Player mid = new Player();
        Player support = new Player();
        for(Player player : players){
            if(player.getRole() == Role.MIDER)mid = player;
            if(player.getRole() == Role.SUPPORT)support = player;
        }
        this.midlane = mid.getLaningSkill() + (mid.getMicroSkill() + support.getLaningSkill()) / 5 +
                pickStage * 0.9 + 10 * calculateRunes();
        return this.midlane;
    }

    protected double calculateRoshan(List<Player> players){
        double roshanFight = 0.0;
        for(Player player : players){
            roshanFight += (player.getTeamfightSkill() + player.getMicroSkill()) * 0.5 + (1 - 0.5) * random.nextDouble();
        }
        return roshanFight;
    }

    protected double calculateTowerDef(List<Player> players){
        this.towerDef = 1.7 * pickStage;
        for(Player player : players){
            this.towerDef += player.getTeamfightSkill() + player.getWardingSkill();
        }
        return this.towerDef;
    }

    protected double calculateTowerPush(List<Player> players){
        this.towerPush = 1.25 * pickStage + 30 * calculateFastPush(players);
        for(Player player : players){
            this.towerPush += 1.1 * player.getTeamfightSkill() + player.getWardingSkill();
        }
        return this.towerPush;
    }

    protected double calculateEarlyGame(List<Player> players){
        this.earlyGame = calculateEasylane(players) + calculateMidlane(players) + calculateHardlane(players);
        return this.earlyGame;
    }

    protected double calculateMidGame(List<Player> players){
        this.midGame += roshan + 7 * calculateRunes();
        for(Player player : players){
            this.midGame += player.getWardingSkill();
            if(player.getRole() != Role.CARRY)this.midGame += player.getTeamfightSkill();
            else this.midGame += 0.33 * player.getTeamfightSkill();
        }
        return this.midGame;
    }

    protected double calculateLateGame(List<Player> players){
        this.lateGame += 3 * calculateRunes();
        for(Player player : players){
            this.lateGame += player.getFarmSkill() + player.getMacroSkill() + player.getTeamfightSkill() +
            player.getWardingSkill() + player.getMicroSkill();
            if(player.getRole() != Role.CARRY)this.lateGame -= 3.5 * player.getGreed();
            else this.lateGame += player.getGreed();
        }
        return this.lateGame;
    }

    protected double calculateChanceToOverDraft(){
        this.chanceToOverdraft = 0.95 + (1.2 - 0.95) * random.nextDouble();
        return this.chanceToOverdraft;
    }

    protected double calculateRunes(){
        this.runes = 1 + (7 - 1) * random.nextDouble();
        return this.runes;
    }

    protected double calculateFastPush(List<Player> players){
        this.fastPush = 0;
        for(Player player : players){
            fastPush += player.getLaningSkill() - 3 * player.getGreed() + player.getHeroPickSkill();
        }
        return Math.max(0, this.fastPush);
    }

    protected double calculateChanceToPickBetter(){
        this.chanceToPickBetter = 0.95 + (1.05 - 0.95) * random.nextDouble();
        return this.chanceToPickBetter;
    }

    public double calculatePerfectExecution(List<Player> players){
        this.supportsMicroActions = 0;
        this.coresMicroActions = 0;
        for(Player player : players){
            if(player.getRole() == Role.CARRY || player.getRole() == Role.OFFLANER ||
            player.getRole() == Role.MIDER)coresMicroActions += player.getMicroSkill();
            else supportsMicroActions += player.getMicroSkill();
        }
        this.perfectExecution = supportsMicroActions + coresMicroActions;
        return this.perfectExecution;
    }

    protected double calculateRandomNonsense(List<Player> players){
        this.randomNonsense = Math.max(100 + (700 - 100) * random.nextDouble(), calculatePerfectExecution(players));
        return this.randomNonsense;
    }

    public double calculateMapPoints(Team team, List<Player> players){
        this.pickStage = 0.0;
        this.easylane = 0.0;
        this.hardlane = 0.0;
        this.midlane = 0.0;
        this.roshan = 0.0;
        this.towerDef = 0.0;
        this.towerPush = 0.0;
        this.earlyGame = 0.0;
        this.midGame = 0.0;
        this.lateGame = 0.0;
        this.chanceToOverdraft = 0.0;
        this.runes = 0.0;
        this.fastPush = 0.0;
        this.chanceToPickBetter = 0.0;
        this.supportsMicroActions = 0.0;
        this.coresMicroActions = 0.0;
        this.perfectExecution = 0.0;
        this.randomNonsense = 0.0;
        return calculateEarlyGame(players) * 1.3 + calculateMidGame(players) * 1.5 + calculateLateGame(players) +
                calculateTowerDef(players) + calculateTowerPush(players) + calculateRoshan(players) +
                team.calculateTeamWork(players) * 10 + team.calculateStressResistance(players) +
                calculatePickStage(players,calculateChanceToOverDraft(),calculateChanceToPickBetter()) * 10 +
                calculateRandomNonsense(players);
    }
}
