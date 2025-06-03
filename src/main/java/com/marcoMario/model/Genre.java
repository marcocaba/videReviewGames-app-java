package com.marcoMario.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Game> games;

    public Genre() {}

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Game> getGames() { return games; }
    public void setGames(List<Game> games) { this.games = games; }
}

