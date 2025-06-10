package com.marcoMario.service;

import com.marcoMario.iService.IReviewService;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Review;
import com.marcoMario.repository.GameRepository;
import com.marcoMario.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ObjectPage getReviewsUser(long idUser, int page) {
        ObjectPage objectPage = new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);

        objectPage.setObjectListReview(reviewRepository.findReviewsByUserIdPage(idUser, pageable));
        objectPage.setSizeList(reviewRepository.getReviewCountByUserId(idUser));

        return objectPage;
    }

    @Override
    public String addReviewUser(long idUser, long idGame, String text, int score) {
        Review review = new Review(idUser, idGame, text, score);
        Review existingReview = reviewRepository.findByIdUserAndIdGame(idUser, idGame);
        String added = "reviewAdded";

        if(existingReview != null){
            added="contains";
        }else {
            reviewRepository.save(review);
        }
        System.out.println(added);
        return added;
    }

    @Override
    public String removeReviewUser(long idUser, long idGame) {
        Review review = reviewRepository.findByIdUserAndIdGame(idUser, idGame);
        String added = "reviewNotFound";

        if (review != null) {
            reviewRepository.delete(review);
            added = "reviewRemoved";
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
}
