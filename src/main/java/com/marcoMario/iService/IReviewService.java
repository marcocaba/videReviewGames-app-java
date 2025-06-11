package com.marcoMario.iService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Review;

import java.util.List;

public interface IReviewService {
    ObjectPage getReviewsUser(int page);

    String addReviewUser(Review review);

    String removeReviewUser(long idGame);

    ObjectPage getReviewsByGame(long idGame, int page);

    double calculateNote(long idGame);

    List<GameDTO> getFourBestGames();

    boolean updateReview(Review review);
}
