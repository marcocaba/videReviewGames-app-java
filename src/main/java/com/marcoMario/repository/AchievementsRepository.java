package com.marcoMario.repository;

import com.marcoMario.model.Achievement;
import com.marcoMario.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AchievementsRepository extends JpaRepository<Achievement, Long> {
    @Query("SELECT a FROM Achievement a WHERE a.game.id = :gameId")
    Set<Achievement> findAchievementsByGameId(@Param("gameId") Long gameId);
}
