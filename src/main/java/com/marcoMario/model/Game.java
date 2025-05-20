package com.marcoMario.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @Id
    private Long id;
    private String name;
    private String description;
    private String released;
    private boolean tba;
    private String website;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Platform> platforms = new ArrayList<Platform>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Achievement> achievements = new ArrayList<Achievement>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Screenshot> screenshots = new ArrayList<>();

    public Game(Long id, String name, String description, String released, boolean tba, String website, List<Platform> platforms, List<Achievement> achievements, List<Screenshot> screenshots) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.released = released;
        this.tba = tba;
        this.website = website;
        this.platforms = platforms;
        this.achievements = achievements;
        this.screenshots = screenshots;
    }

    public Game() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTba() {
        return tba;
    }

    public void setTba(boolean tba) {
        this.tba = tba;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Screenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<Screenshot> screenshots) {
        this.screenshots = screenshots;
    }
}
