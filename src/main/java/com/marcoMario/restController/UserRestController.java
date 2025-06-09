package com.marcoMario.restController;

import com.marcoMario.iService.IGameService;
import com.marcoMario.iService.IUserService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestController {

    @Autowired
    private IUserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/registerUser")
    public boolean registerUser(@RequestParam String nameUser,@RequestParam String password ) {
        return userService.registerUser(nameUser,password);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/viewFavoriteGamesById/{page}")
    public ObjectPage viewFavoriteGamesById(@RequestParam("idUser") long idUser, @PathVariable("page") int page ) {
        return userService.viewFavoriteGamesById(idUser,page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addGameToFavorites")
    public String addGameToFavorites(@RequestParam("idUser") long idUser, @RequestParam("gameId") long gameId ) {
        return userService.addGameToFavorites(idUser,gameId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/removeGameFromFavorites")
    public String removeGameFromFavorites(@RequestParam("idUser") long idUser, @RequestParam("gameId") long gameId ) {
        return userService.removeGameFromFavorites(idUser,gameId);
    }
}
