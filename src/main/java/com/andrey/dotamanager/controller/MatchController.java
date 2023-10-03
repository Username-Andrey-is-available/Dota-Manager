package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Match;
import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.repository.MatchRepository;
import com.andrey.dotamanager.service.MatchResultService;
import com.andrey.dotamanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {

    private final TeamService teamService;
    private final MatchResultService matchResultService;
    private final MatchRepository matchRepository;



    @Autowired
    public MatchController(TeamService teamService, MatchResultService matchResultService,
                           MatchRepository matchRepository) {
        this.teamService = teamService;
        this.matchResultService = matchResultService;
        this.matchRepository = matchRepository;

    }

    @GetMapping("/match/winner")
    public String getMatchWinner(
            @RequestParam Long team1Id,
            @RequestParam Long team2Id,
            @RequestParam int bo
    ) {
        Team team1 = teamService.getTeamById(team1Id);
        Team team2 = teamService.getTeamById(team2Id);

        List<Player> playersTeam1 = team1.getPlayers();
        List<Player> playersTeam2 = team2.getPlayers();

        int team1wins = 0;
        int team2wins = 0;
        StringBuilder resultMessage = new StringBuilder();

        for (int i = 0; i < bo && team1wins < bo / 2 + 1 && team2wins < bo / 2 + 1; ++i) {
            double mapResultTeam1 = matchResultService.calculateMapPoints(team1, playersTeam1);
            double mapResultTeam2 = matchResultService.calculateMapPoints(team2, playersTeam2);

            resultMessage.append("Карта ").append(i + 1).append(": ");

            if (mapResultTeam2 > mapResultTeam1) {
                ++team2wins;
                resultMessage.append("Победа команды ").append(team2.getName());
            } else if (mapResultTeam1 > mapResultTeam2) {
                ++team1wins;
                resultMessage.append("Победа команды ").append(team1.getName());
            } else {
                resultMessage.append("Ничья");
            }

            resultMessage.append(" (").append(team1.getName()).append(": ").append(mapResultTeam1)
                    .append(", ").append(team2.getName()).append(": ").append(mapResultTeam2).append(")\n");
        }

        Team winningTeam;
        if (team1wins > team2wins) {
            winningTeam = team1;
        } else if (team2wins > team1wins) {
            winningTeam = team2;
        } else {
            return "тыдыдыыым надо че-то фиксить" + resultMessage.toString();
        }

        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setWinner(winningTeam);
        matchRepository.save(match);

        return "Победила команда " + winningTeam.getName() + " по количеству выигранных карт " + team1wins + ":" + team2wins + "\n\n" + resultMessage.toString();
    }

}

