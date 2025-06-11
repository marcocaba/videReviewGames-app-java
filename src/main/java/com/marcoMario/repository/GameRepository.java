package com.marcoMario.repository;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface GameRepository extends JpaRepository <Game, Long> {

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g WHERE g.id = :idGame")
    GameDTO getGameById(@Param("idGame") long idGame);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g")
    Page<GameDTO> getAllGamesWithDetailsDTO(Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g ORDER BY function('RANDOM')")
    Page<GameDTO> findRandomGamesDTO(Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g WHERE g.image IS NOT NULL ORDER BY g.released DESC")
    Page<GameDTO> findTopGamesByReleased(Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g JOIN g.genres genre WHERE genre.id = :idGenre")
    Page<GameDTO> getAllGamesFilterByGenre(@Param("idGenre") int idGenre, Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g JOIN g.creators creator WHERE creator.id = :idCreator")
    Page<GameDTO> getAllGamesFilterByCreator(@Param("idCreator") int idCreator, Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g JOIN g.tags tag WHERE tag.id = :idTag")
    Page<GameDTO> getAllGamesFilterByTag(@Param("idTag") int idTag, Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM Game g JOIN g.platforms platform WHERE platform.id = :idPlatform")
    Page<GameDTO> getAllGamesFilterByPlatform(@Param("idPlatform") int idPlatform, Pageable pageable);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image)FROM Game g WHERE LOWER(g.name) LIKE LOWER(CONCAT(:name, '%'))")
    List<GameDTO> findDTOByNameStartsWith(@Param("name") String name, Pageable pageable);

    @Query("SELECT COUNT(g) FROM Game g")
    long countAllGames();

    @Query("SELECT COUNT(g) FROM Game g JOIN g.genres genre WHERE genre.id = :idGenre")
    long countGamesByGenre(@Param("idGenre") int idGenre);

    @Query("SELECT COUNT(g) FROM Game g JOIN g.creators creator WHERE creator.id = :idCreator")
    long countGamesByCreator(@Param("idCreator") int idCreator);

    @Query("SELECT COUNT(g) FROM Game g JOIN g.tags tag WHERE tag.id = :idTag")
    long countGamesByTag(@Param("idTag") int idTag);

    @Query("SELECT COUNT(g) FROM Game g JOIN g.platforms platform WHERE platform.id = :idPlatform")
    long countGamesByPlatform(@Param("idPlatform") int idPlatform);

}
