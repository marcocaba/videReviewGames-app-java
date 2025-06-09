package com.marcoMario.model.DTO;

import com.marcoMario.model.Creator;
import com.marcoMario.model.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Set;

public class GameDTO {

    private Long id;
    private String name;
    private String description;
    private String released;
    private String image;
    private String imageSecond ;
    private Set<Tag> tags;
    private Set<Creator> creators;

    public GameDTO() {
    }

    public GameDTO(Long id, String name, String description, String released, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.released = released;
        this.image = image;
    }

    public GameDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getImageSecond() {
        return imageSecond;
    }

    public void setImageSecond(String imageSecond) {
        this.imageSecond = imageSecond;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Creator> getCreators() {
        return creators;
    }

    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
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
