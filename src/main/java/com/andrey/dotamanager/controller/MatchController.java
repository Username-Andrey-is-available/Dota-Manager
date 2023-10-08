package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Match;
import com.andrey.dotamanager.repository.MatchRepository;
import com.andrey.dotamanager.service.MatchResultService;
import com.andrey.dotamanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
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

    @GetMapping("/winner")
    public ResponseEntity<Match> getMatchWinner(
            @RequestParam Long team1Id,
            @RequestParam Long team2Id,
            @RequestParam int bestOf
    ) {
        Match match = matchResultService.getMatchWinner(team1Id, team2Id, bestOf);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

}



