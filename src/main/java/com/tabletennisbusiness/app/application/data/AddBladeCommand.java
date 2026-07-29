package com.tabletennisbusiness.app.application.data;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record AddBladeCommand(
        @NotNull String brand,
        @NotNull String name,
        MultipartFile avatar
) {}