package com.tabletennisbusiness.app.application;

import com.tabletennisbusiness.app.application.data.AddPlayerCommand;
import com.tabletennisbusiness.app.model.Player;
import com.tabletennisbusiness.app.infrastructure.PlayerJpaRepository;
import com.tabletennisbusiness.app.infrastructure.BladeJpaRepository;
import com.tabletennisbusiness.app.infrastructure.RubberJpaRepository;
import com.tabletennisbusiness.app.model.Blade;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerApplicationService {

    private final PlayerJpaRepository playerJpaRepository;
    private final BladeJpaRepository bladeJpaRepository;
    private final RubberJpaRepository rubberJpaRepository;

    @Inject
    public PlayerApplicationService(PlayerJpaRepository playerJpaRepository, BladeJpaRepository bladeJpaRepository, RubberJpaRepository rubberJpaRepository) {
        this.playerJpaRepository = playerJpaRepository;
        this.bladeJpaRepository = bladeJpaRepository;
        this.rubberJpaRepository = rubberJpaRepository;
    }

    public List<Player> searchPlayers() {
        return playerJpaRepository.findAll();
    }

    public Optional<Player> findPlayer(Long id) {
        return playerJpaRepository.findById(id);
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

        Blade blade = bladeJpaRepository.findById(command.bladeId())
                .orElseThrow(() -> new IllegalArgumentException("Bois introuvable"));
        var forehandRubber = rubberJpaRepository.findById(command.forehandRubberId())
                .orElseThrow(() -> new IllegalArgumentException("Revêtement coup droit introuvable"));
        var backhandRubber = rubberJpaRepository.findById(command.backhandRubberId())
                .orElseThrow(() -> new IllegalArgumentException("Revêtement revers introuvable"));

        Player player = new Player(
                command.name(),
                command.forname(),
                command.handedness(),
                command.countryCode(),
                blade,
                forehandRubber,
                backhandRubber,
                command.information(),
                avatarBase64
        );

        return playerJpaRepository.save(player);
    }
}
