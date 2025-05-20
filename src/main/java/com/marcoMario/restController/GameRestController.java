package com.marcoMario.restController;

import com.marcoMario.iService.IGameService;
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
    @GetMapping("/viewAllGames")
    public List<Game> viewAllGames() {
        return gameService.getAllGames();
    }
}
