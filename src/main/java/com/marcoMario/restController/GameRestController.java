package com.marcoMario.restController;

import com.marcoMario.iService.IGameService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameRestController {

    @Autowired
    private IGameService gameService;

    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/getGameById/{idGame}")
    public Game getGameById(@PathVariable("idGame") int idGame) {
        return gameService.getGameById(idGame);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesDTO")
    public ObjectPage viewGamesDTO(@RequestParam("page") int page) {
        return gameService.getGamesDTO(page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewNewestGamesDTO")
    public List<GameDTO> viewNewestGamesDTO() {
        return gameService.getNewestGamesDTO();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewCarouselGamesDTO")
    public List<GameDTO> viewCarouselGamesDTO() {
        return gameService.getCarouselGamesDTO();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByGenre")
    public ObjectPage viewGamesByGenre(@RequestParam("idGenre") int idGenre, @RequestParam("page") int page) {
        return gameService.getGamesByGenre(idGenre, page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByCreator")
    public ObjectPage viewGamesByCreator(@RequestParam("idCreator") int idCreator, @RequestParam("page") int page) {
        return gameService.getGamesByCreator(idCreator, page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByTag")
    public ObjectPage viewGamesByTag(@RequestParam("idTag") int idTag, @RequestParam("page") int page) {
        return gameService.getGamesByTag(idTag, page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesByPlatform")
    public ObjectPage viewGamesByPlatform(@RequestParam("idPlatform") int idPlatform, @RequestParam("page") int page) {
        return gameService.getGamesByPlatform(idPlatform, page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/gameSearch")
    public List<GameDTO> searchGame(@RequestParam("gameName") String gameName) {
        return gameService.findByNameStartsWith(gameName);
    }
}
