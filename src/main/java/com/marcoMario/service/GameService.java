package com.marcoMario.service;

import com.marcoMario.iService.IGameService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
import com.marcoMario.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private AchievementsRepository achievementsRepository;

    @Autowired
    private ScreenshotsRepository screenshotsRepository;

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private TagRepository tagRepository;


    @Override
    public Game getGameById(long GameId) {
        Game game;
        game = buildCreators(gameRepository.getGameById(GameId));
        game.setCreators(creatorRepository.findCreatorsByGameId(GameId));
        game.setPlatforms(platformRepository.findPlatformsByGameId(GameId));
        game.setAchievements(achievementsRepository.findAchievementsByGameId(GameId));
        game.setScreenshots(screenshotsRepository.findAllByGameId(GameId));
        game.setGenres(genresRepository.findAllByGameId(GameId));
        game.setTags(tagRepository.findAllByGameId(GameId));

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
    public ObjectPage getGamesDTO(int page) {
        ObjectPage objectPage = new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);
        Page<GameDTO> gamesPage = gameRepository.getAllGamesWithDetailsDTO(pageable);
        List<GameDTO> games = gamesPage.getContent();

        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        objectPage.setObjectListGameDTO(gamesDTO);
        objectPage.setSizeList(gameRepository.countAllGames());

        return objectPage;
    }


    private GameDTO buildGameDTO(GameDTO game){
        game.setTags(tagRepository.findAllByGameId(game.getId()));
        game.setCreators(creatorRepository.findCreatorsByGameId(game.getId()));
        game.setImageSecond(screenshotsRepository.findFirstUrlByGameId(game.getId()).getFirst());
        return game;
    }

    @Override
    public List<GameDTO> getNewestGamesDTO() {
        List<GameDTO> games = gameRepository.findTopGamesByReleased(PageRequest.of(0, 4)).getContent();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        return gamesDTO;
    }

    @Override
    public List<GameDTO> getCarouselGamesDTO() {
        List<GameDTO> games = gameRepository.findRandomGamesDTO(PageRequest.of(0, 4)).getContent();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        return gamesDTO;
    }

    @Override
    public ObjectPage getGamesByGenre(int idGenre, int page) {
        ObjectPage objectPage= new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);
        List<GameDTO> games = gameRepository.getAllGamesFilterByGenre(idGenre, pageable).getContent();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        objectPage.setObjectListGameDTO(gamesDTO);
        objectPage.setSizeList(gameRepository.countGamesByGenre(idGenre));

        return objectPage;
    }

    @Override
    public ObjectPage getGamesByCreator(int idCreator, int page) {
        ObjectPage objectPage= new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);
        List<GameDTO> games = gameRepository.getAllGamesFilterByCreator(idCreator, pageable).getContent();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        objectPage.setObjectListGameDTO(gamesDTO);
        objectPage.setSizeList(gameRepository.countGamesByCreator(idCreator));

        return objectPage;
    }

    @Override
    public ObjectPage getGamesByTag(int idTag, int page) {
        ObjectPage objectPage= new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);
        List<GameDTO> games = gameRepository.getAllGamesFilterByTag(idTag, pageable).getContent();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        objectPage.setObjectListGameDTO(gamesDTO);
        objectPage.setSizeList(gameRepository.countGamesByTag(idTag));

        return objectPage;
    }

    @Override
    public ObjectPage getGamesByPlatform(int idPlatform, int page) {
        ObjectPage objectPage= new ObjectPage();
        Pageable pageable = PageRequest.of(page, 20);
        List<GameDTO> games = gameRepository.getAllGamesFilterByPlatform(idPlatform, pageable).getContent();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        objectPage.setObjectListGameDTO(gamesDTO);
        objectPage.setSizeList(gameRepository.countGamesByPlatform(idPlatform));

        return objectPage;
    }

    @Override
    public List<GameDTO> findByNameStartsWith(String gameName) {
        Pageable limit = PageRequest.of(0, 20);
        List<GameDTO> games = gameRepository.findDTOByNameStartsWith(gameName, limit);
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();

        for(GameDTO game:games){
            gamesDTO.add(buildGameDTO(game));
        }
        return gamesDTO;
    }

}
