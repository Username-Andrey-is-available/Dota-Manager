package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.MatchDTO;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.service.MapResultService;
import com.andrey.dotamanager.service.MatchService;
import com.andrey.dotamanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final MapResultService mapResultService;
    private final TeamService teamService;


    @GetMapping
    public List<MatchDTO> getAllMatches() {
        return matchService.getAllMatchesDTO();
    }


    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(
            @RequestParam Long team1Id,
            @RequestParam Long team2Id,
            @RequestParam int bestOf
    ) {
        MatchDTO match = matchService.createMatch(team1Id, team2Id, bestOf);
        return ResponseEntity.ok(match);
    }


    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDTO> getMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.getMatchById(matchId));
    }


    @GetMapping("/showMatchPointsForTeam")
    public Double showMatchPointsForTeam(@RequestParam Long teamId) {
        Team team = teamService.getTeamById(teamId);
        return mapResultService.calculateMapPoints(team, team.getPlayers());
    }
}



