package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.service.PlayerService;
import com.andrey.dotamanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final PlayerService playerService;


    @Autowired
    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/players/by-team")
    public List<Player> getPlayersByTeamId(@RequestParam Long teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        if (team != null) {
            List<Player> players = team.getPlayers();
            for (Player player : players) {
                player.setTeam(null);
            }
            teamService.deleteTeam(id);
        }
    }

}
