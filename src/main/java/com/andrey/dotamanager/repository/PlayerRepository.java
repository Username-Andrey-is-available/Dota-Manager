package com.andrey.dotamanager.repository;

import com.andrey.dotamanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Дополнительные методы для запросов к данным игроков
    List<Player> findByTeamId(Long teamId);

    Player findByNickname(String nickname);
}

