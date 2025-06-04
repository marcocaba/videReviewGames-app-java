package com.marcoMario.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String released;
    private String image;
    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> platforms = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Achievement> achievements;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Screenshot> screenshots;

    @ManyToMany
    @JoinTable(
            name = "game_creators",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id")
    )
    private Set<Creator> creators = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "game_tags",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public Game() {}

    public Game(Long id, String name, String description, String released,
                Set<Platform> platforms, Set<Achievement> achievements, Set<Screenshot> screenshots,
                Set<Creator> creators, Set<Genre> genres, Set<Tag> tags, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.released = released;
        this.platforms = platforms;
        this.achievements = achievements;
        this.screenshots = screenshots;
        this.creators = creators;
        this.genres = genres;
        this.tags = tags;
        this.image = image;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReleased() { return released; }
    public void setReleased(String released) { this.released = released; }

    public Set<Platform> getPlatforms() { return platforms; }
    public void setPlatforms(Set<Platform> platforms) { this.platforms = platforms; }

    public Set<Achievement> getAchievements() { return achievements; }
    public void setAchievements(Set<Achievement> achievements) { this.achievements = achievements; }

    public Set<Screenshot> getScreenshots() { return screenshots; }
    public void setScreenshots(Set<Screenshot> screenshots) { this.screenshots = screenshots; }

    public Set<Creator> getCreators() { return creators; }
    public void setCreators(Set<Creator> creators) { this.creators = creators; }

    public Set<Genre> getGenres() { return genres; }
    public void setGenres(Set<Genre> genres) { this.genres = genres; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
