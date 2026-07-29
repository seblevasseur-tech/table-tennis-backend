package com.tabletennisbusiness.app.application;

import com.tabletennisbusiness.app.application.data.AddRubberCommand;
import com.tabletennisbusiness.app.infrastructure.RubberJpaRepository;
import com.tabletennisbusiness.app.model.Rubber;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RubberApplicationService {

    private final RubberJpaRepository rubberJpaRepository;

    @Inject
    public RubberApplicationService(RubberJpaRepository rubberJpaRepository) {
        this.rubberJpaRepository = rubberJpaRepository;
    }


    public List<Rubber> searchRubbers() {
        return rubberJpaRepository.findAll();
    }

    public Optional<Rubber> findRubber(Long id) {
        return rubberJpaRepository.findById(id);
    }

    public Rubber addRubber(AddRubberCommand command) {
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

        Rubber rubber = new Rubber(
                command.brand(),
                command.name(),
                command.information(),
                avatarBase64
        );

        return rubberJpaRepository.save(rubber);
    }
}
