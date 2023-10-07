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
            @RequestParam int bestOf
    ) {
        return matchResultService.getMatchWinner(team1Id, team2Id, bestOf);
    }
}



