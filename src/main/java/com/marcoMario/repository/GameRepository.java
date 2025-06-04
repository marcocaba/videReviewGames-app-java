package com.marcoMario.repository;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface GameRepository extends JpaRepository <Game, Long> {

    @EntityGraph(attributePaths = {
            "achievements",
            "platforms",
            "screenshots",
            "genres",
            "creators",
            "tags"
    })
    @Query("SELECT g FROM Game g")
    Page<Game> getAllGamesWithDetails(Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g")
    Page<GameDTO> getAllGamesWithDetailsDTO(Pageable pageable);

}
