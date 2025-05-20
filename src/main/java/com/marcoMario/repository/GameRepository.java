package com.marcoMario.repository;

import com.marcoMario.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository <Game, Long> {

    @Query("SELECT DISTINCT g FROM Game g LEFT JOIN FETCH g.achievements LEFT JOIN FETCH g.platforms LEFT JOIN FETCH g.screenshots")
    List<Game> getAllGamesWithDetails();
}
