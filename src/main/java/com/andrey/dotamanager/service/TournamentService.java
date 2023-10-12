package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.model.Tournament;
import com.andrey.dotamanager.repository.TeamRepository;
import com.andrey.dotamanager.repository.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("нет турнира по такому id"));
    }

    public void updateTournament(Long id, Tournament updatedTournament) {
        Tournament existingTournament = tournamentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("нет турнира по такому id"));
        existingTournament.setTournamentName(updatedTournament.getTournamentName());
        existingTournament.setCountry(updatedTournament.getCountry());
        existingTournament.setStartDate(updatedTournament.getStartDate());
        existingTournament.setEndDate(updatedTournament.getEndDate());
        existingTournament.setNumberOfTeams(updatedTournament.getNumberOfTeams());
        existingTournament.setPrizePool(updatedTournament.getPrizePool());

        tournamentRepository.save(existingTournament);
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public void addTeamToTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
        Team team = teamRepository.findById(teamId).orElseThrow(() ->
                new EntityNotFoundException("нет команды по такому id"));

        if (tournament != null && team != null) {
            tournament.getTeams().add(team);
            tournamentRepository.save(tournament);
        }
    }


    public void removeTeamFromTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
        Team team = teamRepository.findById(teamId).orElse(null);

        if (tournament != null && team != null) {
            tournament.getTeams().remove(team);
            tournamentRepository.save(tournament);
        }
    }


}
