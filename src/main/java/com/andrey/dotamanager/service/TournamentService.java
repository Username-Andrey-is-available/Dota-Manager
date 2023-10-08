package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.model.Tournament;
import com.andrey.dotamanager.repository.TeamRepository;
import com.andrey.dotamanager.repository.TournamentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository, TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
    }

    // Создание нового турнира
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    // Получение списка всех турниров
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    // Получение информации о конкретном турнире по его ID

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    // Изменение информации о турнире
    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        Tournament existingTournament = tournamentRepository.findById(id).orElse(null);
        if (existingTournament != null) {
            // Выполняйте здесь обновление свойств турнира на основе updatedTournament
            // Например:
            existingTournament.setTournamentName(updatedTournament.getTournamentName());
            existingTournament.setCountry(updatedTournament.getCountry());
            existingTournament.setStartDate(updatedTournament.getStartDate());
            existingTournament.setEndDate(updatedTournament.getEndDate());
            existingTournament.setNumberOfTeams(updatedTournament.getNumberOfTeams());
            existingTournament.setPrizePool(updatedTournament.getPrizePool());

            return tournamentRepository.save(existingTournament);
        }
        return null;
    }

    // Удаление турнира по его ID
    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public void addTeamToTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
        Team team = teamRepository.findById(teamId).orElse(null);

        if (tournament != null && team != null) {
            tournament.getTeams().add(team); // Добавляем команду к турниру
            tournamentRepository.save(tournament); // Сохраняем изменения
        }
    }



    public void removeTeamFromTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
        Team team = teamRepository.findById(teamId).orElse(null);

        if (tournament != null && team != null) {
            tournament.getTeams().remove(team); // Удаляем команду из списка турнира
            tournamentRepository.save(tournament); // Сохраняем изменения
        }
    }


}
