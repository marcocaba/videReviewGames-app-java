package com.marcoMario.repository;

import com.marcoMario.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Game game JOIN game.tags t WHERE game.id = :gameId")
    Set<Tag> findAllByGameId(@Param("gameId") Long gameId);
}
