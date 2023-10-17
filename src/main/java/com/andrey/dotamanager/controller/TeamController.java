package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.TeamDTO;
import com.andrey.dotamanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;


    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeamsDTO();
    }


    @GetMapping("/{id}")
    public TeamDTO getTeamById(@PathVariable Long id) {
        return teamService.getTeamDTO(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

}
