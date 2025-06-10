package com.marcoMario.iService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;

import java.util.List;

public interface IUserService {

    String registerUser(String nameUser, String password, String SecondPassword);

    ObjectPage viewFavoriteGamesById(long idUser, int page);

    String addGameToFavorites(Long userId, Long gameId);

    String removeGameFromFavorites(Long userId, Long gameId);

    String logInUser(String nameUser, String password);

    long getIdUserByName(String name);
}
