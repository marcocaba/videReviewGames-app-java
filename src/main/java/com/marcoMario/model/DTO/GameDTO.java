package com.marcoMario.model.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class GameDTO {

    @Id
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String released;
    private String image;

    public GameDTO() {
    }

    public GameDTO(Long id, String name, String description, String released, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.released = released;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
