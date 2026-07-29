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
import java.util.Optional;

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

    public Optional<Blade> findBlade(Long id) {
        return bladeJpaRepository.findById(id);
    }

    public Blade updateBlade(Long id, AddBladeCommand command) {
        Blade blade = bladeJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bois introuvable"));
        blade.setBrand(command.brand());
        blade.setName(command.name());
        blade.setInformation(command.information());
        if (command.avatar() != null && !command.avatar().isEmpty()) {
            blade.setAvatar(encodeAvatar(command.avatar()));
        }
        return bladeJpaRepository.save(blade);
    }

    public void deleteBlade(Long id) {
        bladeJpaRepository.deleteById(id);
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
                command.information(),
                avatarBase64
        );

        return bladeJpaRepository.save(blade);
    }

    private String encodeAvatar(org.springframework.web.multipart.MultipartFile avatar) {
        try {
            return "data:" + avatar.getContentType() + ";base64," + Base64.getEncoder().encodeToString(avatar.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du traitement de l'image avatar", e);
        }
    }
}
