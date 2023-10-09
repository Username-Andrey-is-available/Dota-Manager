package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.repository.TeamRepository;
import com.andrey.dotamanager.repository.TournamentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TournamentService tournamentService;

    @Autowired
    public TeamService(TeamRepository teamRepository, TournamentRepository tournamentRepository,
                       TournamentService tournamentService) {
        this.teamRepository = teamRepository;
        this.tournamentService = tournamentService;
    }

    public Team createTeam(String name, double budget, String country, int fans) {
        Team team = new Team();
        team.setName(name);
        team.setBudget(budget);
        team.setCountry(country);
        team.setFans(fans);
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void deleteTeam(Long id) {
        Team team = teamRepository.getTeamById(id);
        if (team != null) {
            List<Player> players = team.getPlayers();
            for (Player player : players) {
                player.setTeam(null);
            }
        }
        teamRepository.deleteById(id);
    }
}
