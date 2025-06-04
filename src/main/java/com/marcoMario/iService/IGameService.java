package com.marcoMario.iService;

import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;

import java.util.List;

public interface IGameService {

    List<Game> getFewGames();

    List<GameDTO> getFewGamesDTO();
}
