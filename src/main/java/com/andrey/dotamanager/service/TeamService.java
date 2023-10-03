package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.repository.MatchRepository;
import com.andrey.dotamanager.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public Team createTeam(String name, double budget, String country) {
        Team team = new Team();
        team.setName(name);
        team.setBudget(budget);
        team.setCountry(country);
        // Установите другие свойства команды, если необходимо
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }


}
