package com.andrey.dotamanager.repository;

import com.andrey.dotamanager.model.Tournament;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    @EntityGraph(attributePaths = "teams")
    Optional<Tournament> findById(Long id);
}
