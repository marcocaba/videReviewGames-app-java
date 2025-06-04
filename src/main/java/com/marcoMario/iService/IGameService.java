package com.marcoMario.iService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;

import java.util.List;

public interface IGameService {

    Game getGameById(long GameId);

    List<GameDTO> getGamesDTO();

    List<GameDTO> getGamesByGenre(int idGenre, int page, int size);

    List<GameDTO> getGamesByCreator(int idCreator, int page, int size);

    List<GameDTO> getGamesByTag(int idTag, int page, int size);

    List<GameDTO> getGamesByPlatform(int idPlatform, int page, int size);

    List<GameDTO> findByNameStartsWith(String gameName);
}
