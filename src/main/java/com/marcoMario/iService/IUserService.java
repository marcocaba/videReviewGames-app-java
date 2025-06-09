package com.marcoMario.iService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;

import java.util.List;

public interface IUserService {

    boolean registerUser(String nameUser, String password);

    ObjectPage viewFavoriteGamesById(long idUser, int page);

    String addGameToFavorites(Long userId, Long gameId);

    String removeGameFromFavorites(Long userId, Long gameId);
}
