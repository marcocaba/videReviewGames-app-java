package com.marcoMario.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String psswd;

    @ManyToMany
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> favorites;


    public Long getidUser() {
        return idUser;
    }

    public void setidUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getpsswd() {
        return psswd;
    }

    public void setpsswd(String psswd) {
        this.psswd = psswd;
    }

    public List<Game> getfavorites() {
        return favorites;
    }

    public void setfavorites(List<Game> favorites) {
        this.favorites = favorites;
    }
}

