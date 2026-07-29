package com.tabletennisbusiness.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "BLADE")
public class Blade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String brand;

    @NotNull
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String avatar;

    public Blade() {
    }

    public Blade(String brand, String name, String avatar) {
        this.brand = brand;
        this.name = name;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }
}
