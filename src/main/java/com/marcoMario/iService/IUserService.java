package com.marcoMario.iService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
import com.marcoMario.model.Platform;
import com.marcoMario.model.User;

import java.util.List;
import java.util.Set;

public interface IUserService {

    String registerUser(String nameUser, String password, String SecondPassword);

    ObjectPage viewFavoriteGamesById(int page);

    String addGameToFavorites(Long gameId);

    String removeGameFromFavorites( Long gameId);

    String logInUser(String nameUser, String password);

    long getIdUserByName(String name);

    boolean logOutUser();

    boolean checkLogInState();

    String getNameUserByIdUser();

    User getUserById(long id);

}
