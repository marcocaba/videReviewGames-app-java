package com.marcoMario.repository;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByNameAndPsswd(String name, String psswd);

    @Query("SELECT new com.marcoMario.model.DTO.GameDTO(g.id, g.name, g.description, g.released, g.image) FROM User u JOIN u.favorites g WHERE u.idUser = :userId")
    List<GameDTO> findFavoritesByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT COUNT(g) FROM User u JOIN u.favorites g WHERE u.idUser = :userId")
    long countFavoritesGamesByUserId(@Param("userId") Long userId);

}
