package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Match;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.service.MapResultService;
import com.andrey.dotamanager.service.MatchResultService;
import com.andrey.dotamanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchResultService matchResultService;
    private final MapResultService mapResultService;
    private final TeamService teamService;


    @GetMapping("/winner")
    public ResponseEntity<Match> getMatchWinner(
            @RequestParam Long team1Id,
            @RequestParam Long team2Id,
            @RequestParam int bestOf
    ) {
        Match match = matchResultService.getMatchWinner(team1Id, team2Id, bestOf);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @GetMapping("/showMatchPointsForTeam")
    public Double showMatchPointsForTeam(@RequestParam Long teamId) {
        Team team = teamService.getTeamById(teamId);
        return mapResultService.calculateMapPoints(team, team.getPlayers());
    }
}



