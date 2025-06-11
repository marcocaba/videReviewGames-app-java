package com.marcoMario.repository;

import com.marcoMario.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findByIdUserAndIdGame(Long idUser, Long idGame);

    List<Review> findByIdGame(Long idGame, Pageable pageable);

    List<Review> findByIdGame(Long idGame);

    long countByIdGame(Long idGame);

    @Query("SELECT r FROM Review r WHERE r.idUser = :idUser")
    List<Review> findReviewsByUserIdPage(Long idUser, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.idUser = :idUser")
    List<Review> findReviewsByUserId(Long idUser);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.idUser = :idUser")
    long getReviewCountByUserId(Long idUser);
}
