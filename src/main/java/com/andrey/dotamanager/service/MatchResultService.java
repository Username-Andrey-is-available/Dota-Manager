package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Match;
import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchResultService {

    private final TeamService teamService;
    private final MapResultService mapResultService;
    private final MatchRepository matchRepository;


    @Autowired
    public MatchResultService(TeamService teamService, MapResultService mapResultService,
                              MatchRepository matchRepository) {
        this.teamService = teamService;
        this.mapResultService = mapResultService;
        this.matchRepository = matchRepository;
    }

    public String getMatchWinner(Long team1Id, Long team2Id, int bestOf) {
        Team team1 = teamService.getTeamById(team1Id);
        Team team2 = teamService.getTeamById(team2Id);

        List<Player> playersTeam1 = team1.getPlayers();
        List<Player> playersTeam2 = team2.getPlayers();

        int team1wins = 0;
        int team2wins = 0;
        StringBuilder resultMessage = new StringBuilder();

        for (int i = 0; i < bestOf && team1wins < bestOf / 2 + 1 && team2wins < bestOf / 2 + 1; ++i) {
            double mapResultTeam1 = mapResultService.calculateMapPoints(team1, playersTeam1);
            double mapResultTeam2 = mapResultService.calculateMapPoints(team2, playersTeam2);

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
            return "тыдыдыыым надо че-то фиксить " + resultMessage;
        }

        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setWinner(winningTeam);
        matchRepository.save(match);

        return "Победила команда " + winningTeam.getName() + " по количеству выигранных карт " +
                team1wins + ":" + team2wins + "\n\n" + resultMessage;
    }
}
