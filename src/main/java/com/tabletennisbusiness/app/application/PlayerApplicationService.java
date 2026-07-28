package com.tabletennisbusiness.app.application;

import com.tabletennisbusiness.app.application.data.AddPlayerCommand;
import com.tabletennisbusiness.app.model.Player;
import com.tabletennisbusiness.app.infrastructure.PlayerJpaRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PlayerApplicationService {

    private final PlayerJpaRepository playerJpaRepository;

    @Inject
    public PlayerApplicationService(PlayerJpaRepository playerJpaRepository) {
        this.playerJpaRepository = playerJpaRepository;
    }

    public List<Player> searchPlayers() {
        return playerJpaRepository.findAll();
    }

    public Player addPlayer(AddPlayerCommand command) {
        try {
            Player player = new Player(command.name(), command.forname(), command.rating(), Arrays.toString(command.avatar().getBytes()));
            return playerJpaRepository.save(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
