package com.marcoMario.service;

import com.marcoMario.iService.IGameService;
import com.marcoMario.model.Creator;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;
import com.marcoMario.repository.CreatorRepository;
import com.marcoMario.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CreatorRepository creatorRepository;


    @Override
    public Game getGameById(long GameId) {
        Game game;
        game = buildCreators(gameRepository.getGameById(GameId));
        game.setCreators(creatorRepository.findCreatorsByGameId(GameId));

        return game;
    }

    private Game buildCreators(GameDTO gameDTO){
        Game game = new Game();
        game.setId(gameDTO.getId());
        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        game.setReleased(gameDTO.getReleased());
        game.setImage(gameDTO.getImage());
        return game;
    }

    @Override
    public List<GameDTO> getGamesDTO() {
        System.out.println("2");
        Pageable pageable = PageRequest.of(0, 20);
        Page<GameDTO> gamesPage = gameRepository.getAllGamesWithDetailsDTO(pageable);
        System.out.println("3");
        return gamesPage.getContent();
    }

    @Override
    public List<GameDTO> getGamesByGenre(int idGenre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gameRepository.getAllGamesFilterByGenre(idGenre, pageable).getContent();
    }

    @Override
    public List<GameDTO> getGamesByCreator(int idCreator, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gameRepository.getAllGamesFilterByCreator(idCreator, pageable).getContent();
    }

    @Override
    public List<GameDTO> getGamesByTag(int idTag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gameRepository.getAllGamesFilterByTag(idTag, pageable).getContent();
    }

    @Override
    public List<GameDTO> getGamesByPlatform(int idPlatform, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gameRepository.getAllGamesFilterByPlatform(idPlatform, pageable).getContent();
    }

    @Override
    public List<GameDTO> findByNameStartsWith(String gameName) {
        Pageable limit = PageRequest.of(0, 30);
        return gameRepository.findDTOByNameStartsWith(gameName, limit);
    }

}
