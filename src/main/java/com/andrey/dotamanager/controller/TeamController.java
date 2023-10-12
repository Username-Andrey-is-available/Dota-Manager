package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;


    @GetMapping(value = "/all")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

}
