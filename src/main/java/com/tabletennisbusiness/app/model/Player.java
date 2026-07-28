package com.tabletennisbusiness.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String forname;

    @NotNull
    private Integer rating;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String avatar;

    public Player() {
    }

    public Player(String name, String forname, Integer rating, String avatar) {
        this.name = name;
        this.forname = forname;
        this.rating = rating;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getForname() {
        return forname;
    }

    public Integer getRating() {
        return rating;
    }

    public String getAvatar() {
        return avatar;
    }
}
