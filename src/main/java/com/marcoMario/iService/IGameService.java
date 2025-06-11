package com.marcoMario.iService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;

import java.util.List;

public interface IGameService {

    Game getGameById(long GameId);

    ObjectPage getGamesDTO(int page);

    List<GameDTO> getBestGamesDTO(List<Long> idGames);

    List<GameDTO> getNewestGamesDTO();

    List<GameDTO> getCarouselGamesDTO();

    ObjectPage getGamesByGenre(int idGenre, int page);

    ObjectPage getGamesByCreator(int idCreator, int page);

    ObjectPage getGamesByTag(int idTag, int page);

    ObjectPage getGamesByPlatform(int idPlatform, int page);

    List<GameDTO> findByNameStartsWith(String gameName);
}
