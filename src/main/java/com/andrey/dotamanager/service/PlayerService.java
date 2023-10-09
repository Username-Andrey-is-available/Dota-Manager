package com.andrey.dotamanager.service;

import com.andrey.dotamanager.model.Player;
import com.andrey.dotamanager.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> getPlayersByTeamId(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }


    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }


    public Player getPlayerByNickname(String nickname) {

        return playerRepository.findByNickname(nickname);
    }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }
}