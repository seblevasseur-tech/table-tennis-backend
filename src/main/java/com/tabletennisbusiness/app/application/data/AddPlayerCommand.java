package com.tabletennisbusiness.app.application.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import com.tabletennisbusiness.app.model.Handedness;

public record AddPlayerCommand(
        @NotBlank String name,
        @NotBlank String forname,
        @NotNull Handedness handedness,
        @NotBlank String countryCode,
        @NotNull Long bladeId,
        @NotNull Long forehandRubberId,
        @NotNull Long backhandRubberId,
        String information,
        MultipartFile avatar
) {}