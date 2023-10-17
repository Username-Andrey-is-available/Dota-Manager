package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.model.TeamDTO;
import com.andrey.dotamanager.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper = Mappers.getMapper(TeamMapper.class);


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

    public List<TeamDTO> getAllTeamsDTO() {
        List<Team> teams = getAllTeams();
        return teamMapper.teamsToTeamsDTOs(teams);
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("нет команды по такому id"));
    }

    public TeamDTO getTeamDTO(Long id) {
        Team team = getTeamById(id);
        return teamMapper.teamToTeamDTO(team);
    }

    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("для удаления нет команды по такому id"));
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            player.setTeam(null);
        }
        teamRepository.deleteById(id);
    }
}
