package com.marcoMario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    private String percent;

    @ManyToOne()
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private Game game;


    public Achievement(int id, String name, String description, String image, String percent, Game game) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.percent = percent;
        this.game = game;
    }

    public Achievement() {

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
