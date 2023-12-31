package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Match;
import com.andrey.dotamanager.model.MatchDTO;
import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.model.Team;
import com.andrey.dotamanager.repository.MatchRepository;
import com.andrey.dotamanager.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final TeamService teamService;
    private final MapResultService mapResultService;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final MatchMapper matchMapper;

    public MatchDTO createMatch(Long team1Id, Long team2Id, int bestOf) {
        Team team1 = teamService.getTeamById(team1Id);
        Team team2 = teamService.getTeamById(team2Id);

        List<Player> playersTeam1 = team1.getPlayers();
        List<Player> playersTeam2 = team2.getPlayers();

        int team1wins = 0;
        int team2wins = 0;

        for (int i = 0; i < bestOf && team1wins < bestOf / 2 + 1 && team2wins < bestOf / 2 + 1; ++i) {
            double mapResultTeam1 = mapResultService.calculateMapPoints(team1, playersTeam1);
            double mapResultTeam2 = mapResultService.calculateMapPoints(team2, playersTeam2);

            if (mapResultTeam2 > mapResultTeam1) {
                ++team2wins;
            } else {
                ++team1wins;
            }
        }

        Team winningTeam;
        if (team1wins > team2wins) {
            winningTeam = team1;
        } else {
            winningTeam = team2;
        }

        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setWinner(winningTeam);
        matchRepository.save(match);

        return getMatchDTO(match);
    }

    public MatchDTO getMatchById(Long matchId) {
        return getMatchDTO(matchRepository.findById(matchId).orElseThrow(() ->
                new EntityNotFoundException("нет матча по такому id")));
    }

    public Team getMatchWinnerById(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(EntityNotFoundException::new);
        return teamRepository.findTeamByWonMatchesContaining(match)
                .orElseThrow(() -> new EntityNotFoundException("нет победителя матча по такому id матча"));
    }

    public MatchDTO getMatchDTO(Match match) {
        return matchMapper.matchToMatchDTO(match);
    }

    public List<MatchDTO> getAllMatchesDTO() {
        List<Match> matches = getAllMatches();
        return matchMapper.matchesToMatchesDTO(matches);
    }

    private List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}

