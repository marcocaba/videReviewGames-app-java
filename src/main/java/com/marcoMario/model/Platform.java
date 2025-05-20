package com.marcoMario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String slug;
    private String name;
    private String release_at;
    private String requirementsMinimum;
    private String requirementsRecommended;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private Game game;


    public Platform(Game game, String name, String slug, int id, String release_at, String requirementsMinimum, String requirementsRecommended) {
        this.game = game;
        this.name = name;
        this.slug = slug;
        this.id = id;
        this.release_at = release_at;
        this.requirementsMinimum = requirementsMinimum;
        this.requirementsRecommended = requirementsRecommended;
    }

    public Platform() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRelease_at() {
        return release_at;
    }

    public void setRelease_at(String release_at) {
        this.release_at = release_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirementsMinimum() {
        return requirementsMinimum;
    }

    public void setRequirementsMinimum(String requirementsMinimum) {
        this.requirementsMinimum = requirementsMinimum;
    }

    public String getRequirementsRecommended() {
        return requirementsRecommended;
    }

    public void setRequirementsRecommended(String requirementsRecommended) {
        this.requirementsRecommended = requirementsRecommended;
    }
}
