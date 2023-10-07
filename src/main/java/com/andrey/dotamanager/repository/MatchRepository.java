package com.andrey.dotamanager.repository;

import com.andrey.dotamanager.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findMatchByTeam1IdAndTeam2Id(Long team1Id, Long team2Id);
}
