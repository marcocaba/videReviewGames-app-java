package com.marcoMario.service;

import com.marcoMario.iService.IGameService;
import com.marcoMario.iService.IReviewService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.LoggedUser;
import com.marcoMario.model.Review;
import com.marcoMario.repository.GameRepository;
import com.marcoMario.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private IGameService gameService;

    @Override
    public ObjectPage getReviewsUser(int page) {
        Long idUser = LoggedUser.userId;
        ObjectPage objectPage = null;

        if (idUser != null){
            objectPage= new ObjectPage();
            Pageable pageable = PageRequest.of(page, 20);

            objectPage.setObjectListReview(reviewRepository.findReviewsByUserIdPage(idUser, pageable));
            objectPage.setSizeList(reviewRepository.getReviewCountByUserId(idUser));
        }

        return objectPage;
    }

    @Override
    public String addReviewUser(Review review) {
        Long idUser = LoggedUser.userId;
        String added = "needLogIn";
        Review newReview = new Review();

        if (idUser != null){
            review.setIdUser(idUser);
            Review existingReview = reviewRepository.findByIdUserAndIdGame(idUser, review.getIdGame());
            added = "reviewAdded";

            if(existingReview != null){
                added="contains";
            }else {
                newReview.setIdUser(review.getIdUser());
                newReview.setText(review.getText());
                newReview.setScore(review.getScore());
                newReview.setIdGame(review.getIdGame());
                reviewRepository.save(newReview);
            }
        }

        return added;
    }

    @Override
    public String removeReviewUser(long idGame) {
        Long idUser = LoggedUser.userId;
        String added = "needLogIn";

        if (idUser != null){
            Review review = reviewRepository.findByIdUserAndIdGame(idUser, idGame);
            added = "reviewNotFound";

            if (review != null) {
                reviewRepository.delete(review);
                added = "reviewRemoved";
            }
        }

        return added;
    }

    @Override
    public ObjectPage getReviewsByGame(long idGame, int page) {
        ObjectPage objectPage = new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);

        objectPage.setObjectListReview(reviewRepository.findByIdGame(idGame, pageable));
        objectPage.setSizeList(reviewRepository.countByIdGame(idGame));

        return objectPage;
    }

    @Override
    public double calculateNote(long idGame) {
        List<Review> reviews = reviewRepository.findByIdGame(idGame);

        if (reviews.isEmpty()) return 0.0;

        double suma = reviews.stream()
                .mapToInt(Review::getScore)
                .sum();

        return Math.round((suma / (double) reviews.size()) * 10.0) / 10.0;
    }

    @Override
    public List<GameDTO> getFourBestGames() {
        return gameService.getBestGamesDTO(getIdBestGames());
    }

    @Override
    public boolean updateReview(Review review) {
        Long idUser = LoggedUser.userId;
        Review existingReview = reviewRepository.findByIdUserAndIdGame(idUser, review.getIdGame());

        existingReview.setText(review.getText());
        existingReview.setScore(review.getScore());

        reviewRepository.save(existingReview);

        return true;
    }

    private List<Long> getIdBestGames() {
        List<Review> all = reviewRepository.findAll();

        return all.stream()
                .collect(Collectors.groupingBy(
                        Review::getIdGame,
                        Collectors.averagingInt(Review::getScore)
                ))
                .entrySet()
                .stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue())) // orden descendente
                .limit(4)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
