package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Role;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@PropertySource("classpath:mapResult.properties")
public class MapResultService {

    @Value("${earlyGameCoefficient}")
    private double earlyGameCoefficient;

    @Value("${midGameCoefficient}")
    private double midGameCoefficient;

    @Value("${lateGameCoefficient}")
    private double lateGameCoefficient;

    @Value("${towerDefCoefficient}")
    private double towerDefCoefficient;

    @Value("${towerPushCoefficient}")
    private double towerPushCoefficient;

    @Value("${roshanCoefficient}")
    private double roshanCoefficient;

    @Value("${teamWorkCoefficient}")
    private double teamWorkCoefficient;

    @Value("${stressResistanceCoefficient}")
    private double stressResistanceCoefficient;

    @Value("${pickStageCoefficient1}")
    private double pickStageCoefficient1;

    @Value("${pickStageCoefficient2}")
    private double pickStageCoefficient2;

    @Value("${randomNonsenseCoefficient}")
    private double randomNonsenseCoefficient;

    @Value("${perfectExecutionCoefficient}")
    private double perfectExecutionCoefficient;

    private final RandomGenerator randomGenerator = new RandomGenerator();


    public double calculateMapPoints(Team team, List<Player> players) {
        return calculateEarlyGame(players) * earlyGameCoefficient *
                calculateMidGame(players) * midGameCoefficient *
                calculateLateGame(players) * lateGameCoefficient *
                calculateTowerDef(players) * towerDefCoefficient *
                calculateTowerPush(players) * towerPushCoefficient *
                calculateRoshan(players) * roshanCoefficient *
                team.calculateTeamWork(players) * teamWorkCoefficient *
                team.calculateStressResistance(players) * stressResistanceCoefficient *
                calculatePickStage(players, calculateChanceToOverDraft(),
                        calculateChanceToPickBetter()) * pickStageCoefficient1 *
                calculateRandomNonsense(players) * randomNonsenseCoefficient;
    }

    private double calculateEarlyGame(List<Player> players) {
        return calculateEasylane(players) + calculateMidlane(players) + calculateHardlane(players);
    }

    private double calculateHardlane(List<Player> players) {
        Player offlane = null;
        Player support = null;
        for(Player player : players){
            if(player.getRole() == Role.OFFLANER){
                offlane = player;
            }
            if(player.getRole() == Role.SUPPORT){
                support = player;
            }
        }
        return (offlane != null ? offlane.getLaningSkill() : 0.1) *
                ((offlane != null ? offlane.getMicroSkill() : 0.1) *
                        (support != null ? support.getMicroSkill() : 0.1)) *
                (support != null ? support.getLaningSkill() : 0.1) *
                calculatePickStage(players, calculateChanceToOverDraft(), calculateChanceToPickBetter());
    }

    private double calculateMidlane(List<Player> players) {
        Player mider = null;
        Player support = null;
        for (Player player : players) {
            if (player.getRole() == Role.MIDER) {
                mider = player;
            }
            if (player.getRole() == Role.SUPPORT) {
                support = player;
            }
        }
        return (mider != null ? mider.getLaningSkill() : 0.1) *
                ((mider != null ? mider.getMicroSkill() : 0.1) *
                        (support != null ? support.getLaningSkill() : 0.1)) *
                calculatePickStage(players, calculateChanceToOverDraft(), calculateChanceToPickBetter()) *
                Math.max(calculateRunes(), 0.5);
    }

    private double calculateEasylane(List<Player> players) {
        Player carry = null;
        Player hardsupport = null;
        for (Player player : players) {
            if (player.getRole() == Role.CARRY) {
                carry = player;
            }
            if (player.getRole() == Role.HARDSUPPORT) {
                hardsupport = player;
            }
        }
        return (carry != null ? carry.getLaningSkill() : 0.1) * ((carry != null ? carry.getMicroSkill() : 0.1) *
                (hardsupport != null ? hardsupport.getMicroSkill() : 0.1)) *
                (hardsupport != null ? hardsupport.getLaningSkill() : 0.1) *
                calculatePickStage(players, calculateChanceToOverDraft(), calculateChanceToPickBetter());
    }

    private double calculateMidGame(List<Player> players) {
        double midGame = Math.max(calculateRunes(), 0.75);
        for (Player player : players) {
            midGame *= player.getWardingSkill() * player.getTeamfightSkill() *
                    player.getFarmSkill() * player.getMicroSkill() * player.getMacroSkill();
        }
        return midGame;
    }

    private double calculateLateGame(List<Player> players) {
        double lateGame = Math.max(calculateRunes(), 0.85);
        for (Player player : players) {
            lateGame *= player.getFarmSkill() * player.getMacroSkill() * player.getTeamfightSkill() *
                    player.getWardingSkill() * player.getMicroSkill() * (1 - player.getGreed());
        }
        return lateGame;
    }

    private double calculateRunes() {
        return 0.5 + (1 - 0.5) * RandomGenerator.getRandomDouble();
    }

    private double calculateTowerDef(List<Player> players) {
        double towerDef = 0;
        for (Player player : players) {
            if (player.getRole() != Role.CARRY) {
                towerDef *= player.getTeamfightSkill() * player.getWardingSkill() *
                        0.95 + (1.05 - 0.95) * RandomGenerator.getRandomDouble();
            }
        }
        return towerDef;
    }

    private double calculateTowerPush(List<Player> players) {
        double towerPush = 0;
        for (Player player : players) {
            towerPush *= player.getTeamfightSkill() * player.getWardingSkill() *
                    0.95 + (1.05 - 0.95) * RandomGenerator.getRandomDouble();
        }
        return towerPush;
    }

    private double calculateRoshan(List<Player> players) {
        double roshanFight = 0;
        for (Player player : players) {
            roshanFight *= player.getTeamfightSkill() * player.getMicroSkill() *
                    0.9 + (1.1 - 0.9) * RandomGenerator.getRandomDouble();
        }
        return roshanFight;
    }

    private double calculatePickStage(List<Player> players, double chanceToOverDraft, double chanceToPickBetter) {
        Player mider = null;
        Player captain = null;
        for (Player player : players) {
            if (player.getRole() == Role.MIDER) {
                mider = player;
            }
            if (player.isCaptain()) {
                captain = player;
            }
        }
        return ((captain != null ? captain.getHeroPickSkill() : 0.1) * (mider != null ? mider.getHeroPickSkill() : 0.1)) *
                Math.max(chanceToOverDraft, chanceToPickBetter);
    }

    private double calculateChanceToOverDraft() {
        return 0.95 + (1.2 - 0.95) * RandomGenerator.getRandomDouble();
    }

    private double calculateChanceToPickBetter() {
        return 0.95 + (1.05 - 0.95) * RandomGenerator.getRandomDouble();
    }

    private double calculateRandomNonsense(List<Player> players) {
        return Math.max(0.85 + (1.1 - 0.85) * RandomGenerator.getRandomDouble(),
                calculatePerfectExecution(players) * perfectExecutionCoefficient);
    }

    private double calculatePerfectExecution(List<Player> players) {
        double perfectExecution = 1;
        for (Player player : players) {
            perfectExecution *= player.getMicroSkill();
        }
        return perfectExecution;
    }
}
