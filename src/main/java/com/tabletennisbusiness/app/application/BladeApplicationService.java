package com.tabletennisbusiness.app.application;

import com.tabletennisbusiness.app.application.data.AddBladeCommand;
import com.tabletennisbusiness.app.infrastructure.BladeJpaRepository;
import com.tabletennisbusiness.app.model.Blade;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class BladeApplicationService {

    private final BladeJpaRepository bladeJpaRepository;

    @Inject
    public BladeApplicationService(BladeJpaRepository bladeJpaRepository) {
        this.bladeJpaRepository = bladeJpaRepository;
    }


    public List<Blade> searchBlades() {
        return bladeJpaRepository.findAll();
    }

    public Blade addBlade(AddBladeCommand command) {
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

        Blade blade = new Blade(
                command.brand(),
                command.name(),
                avatarBase64
        );

        return bladeJpaRepository.save(blade);
    }
}
