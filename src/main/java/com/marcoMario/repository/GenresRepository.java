package com.marcoMario.repository;

import com.marcoMario.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface GenresRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Game game JOIN game.genres g WHERE game.id = :gameId")
    Set<Genre> findAllByGameId(@Param("gameId") Long gameId);
}
