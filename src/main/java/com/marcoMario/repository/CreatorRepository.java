package com.marcoMario.repository;

import com.marcoMario.model.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    Optional<Creator> findById(Long id);

    @Query("SELECT c FROM Game g JOIN g.creators c WHERE g.id = :gameId")
    Set<Creator> findCreatorsByGameId(@Param("gameId") Long gameId);
}
