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

    @NotNull
    private String avatar;

    public Player() {
    }

    public Player(String name, String forname, Integer rating, String avatar) {
        this.name = name;
        this.forname = forname;
        this.rating = rating;
        this.avatar = avatar;
    }

    public Long id() {
        return id;
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

    public String avatar() {
        return avatar;
    }
}
