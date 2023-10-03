package com.andrey.dotamanager.repository;

import com.andrey.dotamanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    // Дополнительные методы, если необходимо
}
