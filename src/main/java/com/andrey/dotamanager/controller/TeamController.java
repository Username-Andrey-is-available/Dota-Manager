package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.service.PlayerService;
import com.andrey.dotamanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final PlayerService playerService;


    @GetMapping(value = "/players/by-team", produces = "application/json")
    public List<Player> getPlayersByTeamId(@RequestParam Long teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }


    @GetMapping(value = "/all")
    public List<Team> getAllTeams(){return teamService.getAllTeams();}

    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

}
