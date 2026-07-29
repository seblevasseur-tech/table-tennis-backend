package com.tabletennisbusiness.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rubber")
public class Rubber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String brand;

    @NotNull
    private String name;

    @Column(columnDefinition = "TEXT")
    private String information;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    public Rubber() {
    }

    public Rubber(String brand, String name, String information, String avatar) {
        this.brand = brand;
        this.name = name;
        this.information = information;
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

    public String getInformation() {
        return information;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setInformation(String information) {
        this.information = information;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}
