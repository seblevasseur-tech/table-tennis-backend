package com.tabletennisbusiness.app.application.data;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record AddPlayerCommand(
        @NotNull String name,
        @NotNull String forname,
        @NotNull Integer rating,
        MultipartFile avatar
) {}