package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.service.PlayerService;
import com.andrey.dotamanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    // Реализация методов контроллера для работы с данными команды
    @GetMapping("/players/by-team")
    public List<Player> getPlayersByTeamId(@RequestParam Long teamId) {
        // Здесь предполагается, что у вас есть метод в сервисе playerService, который
        // позволяет получить список игроков по ID команды. Вызовите этот метод и верните результат.
        return playerService.getPlayersByTeamId(teamId);
    }


}
