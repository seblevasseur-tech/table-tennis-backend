package com.tabletennisbusiness.app.application.data;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class AddPlayerCommand {

    @NotNull
    private String name;

    @NotNull
    private String forname;

    @NotNull
    private Integer rating;

    @Lob
    MultipartFile avatarFile;

    public AddPlayerCommand(String name, String forname, Integer rating, MultipartFile avatarFile) {
        this.name = name;
        this.forname = forname;
        this.rating = rating;
        this.avatarFile = avatarFile;
    }

    public String name() {
        return name;
    }

    public String forname() {
        return forname;
    }

    public Integer rating() {
        return rating;
    }

    public MultipartFile avatarFile() {
        return avatarFile;
    }
}
