package com.andrey.dotamanager.controller;

import com.andrey.dotamanager.model.Tournament;
import com.andrey.dotamanager.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping("/create")
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @PutMapping("/{id}")
    public void updateTournament(@PathVariable Long id, @RequestBody Tournament updatedTournament) {
        tournamentService.updateTournament(id, updatedTournament);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }

    @PostMapping("/{tournamentId}/add-team/{teamId}")
    public Tournament addTeamToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long teamId
    ) {
        tournamentService.addTeamToTournament(tournamentId, teamId);
        return null;
    }

    @DeleteMapping("/{tournamentId}/remove-team/{teamId}")
    public void removeTeamFromTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long teamId
    ) {
        tournamentService.removeTeamFromTournament(tournamentId, teamId);
    }

}
