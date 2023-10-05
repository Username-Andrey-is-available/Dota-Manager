package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.service.PlayerService;
import com.andrey.dotamanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public void addPlayer(@RequestBody Player player) {
        playerService.savePlayer(player);
    }

    @PutMapping("/update/{id}")
    public void updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Player existingPlayer = playerService.getPlayerById(id);
        if (existingPlayer != null) {
            // Обновите поля существующего игрока данными из player
            existingPlayer.setName(player.getName());
            existingPlayer.setSalary(player.getSalary());
            // и так далее...
            playerService.savePlayer(existingPlayer);
        }
    }


    @GetMapping("/byTeam/{teamId}")
    public List<Player> getPlayersByTeam(@PathVariable Long teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }

    @PutMapping("/changeTeam/{nickname}")
    public ResponseEntity<String> changePlayerTeam(
            @PathVariable String nickname,
            @RequestParam Long newTeamId
    ) {
        // Retrieve the player by nickname
        Player player = playerService.getPlayerByNickname(nickname);

        if (player == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }

        // Retrieve the new team by ID
        Team newTeam = teamService.getTeamById(newTeamId);

        if (newTeam == null) {
            return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
        }

        // Update the player's team
        player.setTeam(newTeam);
        playerService.updatePlayer(player);

        return new ResponseEntity<>("Player's team updated successfully", HttpStatus.OK);
    }
}
