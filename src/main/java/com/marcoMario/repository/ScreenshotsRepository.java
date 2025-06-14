package com.marcoMario.repository;


import com.marcoMario.model.Screenshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ScreenshotsRepository extends JpaRepository<Screenshot, Long> {

    @Query("SELECT s FROM Screenshot s WHERE s.game.id = :gameId")
    Set<Screenshot> findAllByGameId(@Param("gameId") Long gameId);

    @Query("SELECT s.url FROM Screenshot s WHERE s.game.id = :gameId ORDER BY s.id ASC")
    List<String> findFirstUrlByGameId(@Param("gameId") Long gameId);

}
