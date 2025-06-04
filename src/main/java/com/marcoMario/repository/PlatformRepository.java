package com.marcoMario.repository;

import com.marcoMario.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Query(value = "SELECT p.* FROM platforms p JOIN game_platforms gp ON p.id = gp.platform_id WHERE gp.game_id = :gameId", nativeQuery = true)
    Set<Platform> findPlatformsByGameId(@Param("gameId") Long gameId);
}
