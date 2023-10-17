package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.PlayerDTO;
import com.andrey.dotamanager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;


    @GetMapping(value = "/team/{teamId}", produces = "application/json")
    public List<PlayerDTO> getPlayersByTeamId(@PathVariable Long teamId) {
        return playerService.getPlayersDTOByTeamId(teamId);
    }

    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayersDTO();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public void addPlayer(@RequestBody Player player) {
        playerService.savePlayer(player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }

}
