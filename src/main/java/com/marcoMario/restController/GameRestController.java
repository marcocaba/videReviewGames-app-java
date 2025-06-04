package com.marcoMario.restController;

import com.marcoMario.iService.IGameService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRestController {

    @Autowired
    private IGameService gameService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewFewGames")
    public List<Game> viewFewGames() {
        return gameService.getFewGames();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewGamesDTO")
    public List<GameDTO> viewGamesDTO() {
        System.out.println("1");
        return gameService.getFewGamesDTO();
    }
}
