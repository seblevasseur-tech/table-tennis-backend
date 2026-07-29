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

    @Enumerated(EnumType.STRING)
    @NotNull
    private Handedness handedness;

    @NotNull
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "blade_id", nullable = false)
    private Blade blade;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "forehand_rubber_id", nullable = false)
    private Rubber forehandRubber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "backhand_rubber_id", nullable = false)
    private Rubber backhandRubber;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String information;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String avatar;

    public Player() {
    }

    public Player(String name, String forname, Handedness handedness, String countryCode, Blade blade, Rubber forehandRubber, Rubber backhandRubber, String information, String avatar) {
        this.name = name;
        this.forname = forname;
        this.handedness = handedness;
        this.countryCode = countryCode;
        this.blade = blade;
        this.forehandRubber = forehandRubber;
        this.backhandRubber = backhandRubber;
        this.information = information;
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

    public Handedness getHandedness() {
        return handedness;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Blade getBlade() {
        return blade;
    }

    public Rubber getForehandRubber() {
        return forehandRubber;
    }

    public Rubber getBackhandRubber() {
        return backhandRubber;
    }

    public String getInformation() {
        return information;
    }

    public String getAvatar() {
        return avatar;
    }
}
