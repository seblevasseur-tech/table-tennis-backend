package com.tabletennisbusiness.app.application;

import com.tabletennisbusiness.app.application.data.AddPlayerCommand;
import com.tabletennisbusiness.app.model.Player;
import com.tabletennisbusiness.app.infrastructure.PlayerJpaRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
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
        String avatarBase64 = null;

        // 1. Vérification contre le NullPointerException
        if (command.avatar() != null && !command.avatar().isEmpty()) {
            try {
                byte[] bytes = command.avatar().getBytes();
                String contentType = command.avatar().getContentType(); // ex: image/png, image/jpeg

                // 2. Formatage en Data URI Base64 lisible directement par Angular
                avatarBase64 = "data:" + contentType + ";base64," + Base64.getEncoder().encodeToString(bytes);
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors du traitement de l'image avatar", e);
            }
        }

        Player player = new Player(
                command.name(),
                command.forname(),
                command.rating(),
                avatarBase64
        );

        return playerJpaRepository.save(player);
    }
}
