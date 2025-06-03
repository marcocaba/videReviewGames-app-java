package com.marcoMario.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

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

    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platform> platforms = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Screenshot> screenshots = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_creators",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id")
    )
    private List<Creator> creators = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_developers",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> developers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_tags",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();


    public Game() {}

    public Game(Long id, String name, String description, String released, boolean tba, String website,
                List<Platform> platforms, List<Achievement> achievements, List<Screenshot> screenshots,
                List<Creator> creators, List<Developer> developers, List<Genre> genres, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.released = released;
        this.tba = tba;
        this.website = website;
        this.platforms = platforms;
        this.achievements = achievements;
        this.screenshots = screenshots;
        this.creators = creators;
        this.developers = developers;
        this.genres = genres;
        this.tags = tags;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReleased() { return released; }
    public void setReleased(String released) { this.released = released; }

    public boolean isTba() { return tba; }
    public void setTba(boolean tba) { this.tba = tba; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public List<Platform> getPlatforms() { return platforms; }
    public void setPlatforms(List<Platform> platforms) { this.platforms = platforms; }

    public List<Achievement> getAchievements() { return achievements; }
    public void setAchievements(List<Achievement> achievements) { this.achievements = achievements; }

    public List<Screenshot> getScreenshots() { return screenshots; }
    public void setScreenshots(List<Screenshot> screenshots) { this.screenshots = screenshots; }

    public List<Creator> getCreators() { return creators; }
    public void setCreators(List<Creator> creators) { this.creators = creators; }

    public List<Developer> getDevelopers() { return developers; }
    public void setDevelopers(List<Developer> developers) { this.developers = developers; }

    public List<Genre> getGenres() { return genres; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }
}

