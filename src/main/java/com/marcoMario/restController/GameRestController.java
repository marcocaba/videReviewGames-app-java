package com.marcoMario.restController;

import com.marcoMario.iService.IGameService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRestController {

    @Autowired
    private IGameService gameService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getGameById")
    public Game getGameById(@RequestParam("idGame") int idGame) {
        return gameService.getGameById(idGame);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesDTO")
    public List<GameDTO> viewGamesDTO() {
        return gameService.getGamesDTO();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewNewestGamesDTO")
    public List<GameDTO> viewNewestGamesDTO() {
        return gameService.getNewestGamesDTO();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByGenre")
    public List<GameDTO> viewGamesByGenre(@RequestParam("idGenre") int idGenre, @RequestParam("page") int page, @RequestParam("size") int size) {
        return gameService.getGamesByGenre(idGenre, page, size);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByCreator")
    public List<GameDTO> viewGamesByCreator(@RequestParam("idCreator") int idCreator, @RequestParam("page") int page, @RequestParam("size") int size) {
        return gameService.getGamesByCreator(idCreator, page, size);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByTag")
    public List<GameDTO> viewGamesByTag(@RequestParam("idTag") int idTag, @RequestParam("page") int page, @RequestParam("size") int size) {
        return gameService.getGamesByTag(idTag, page, size);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByPlatform")
    public List<GameDTO> viewGamesByPlatform(@RequestParam("idPlatform") int idPlatform, @RequestParam("page") int page, @RequestParam("size") int size) {
        return gameService.getGamesByPlatform(idPlatform, page, size);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/gameSearch")
    public List<GameDTO> searchGame(@RequestParam("gameName") String gameName) {
        return gameService.findByNameStartsWith(gameName);
    }
}
