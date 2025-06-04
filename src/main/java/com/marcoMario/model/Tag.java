package com.marcoMario.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Game> games;

    public Tag() {}

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}

