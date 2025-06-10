package com.marcoMario.iService;


import com.marcoMario.model.DTO.ObjectPage;

public interface IReviewService {
    ObjectPage getReviewsUser(long idUser, int page);

    String addReviewUser(long idUser, long idGame, String text, int score);
    
    String removeReviewUser(long idUser, long idGame);

    ObjectPage getReviewsByGame(long idGame, int page);
}
