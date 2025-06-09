package com.marcoMario.restController;

import com.marcoMario.iService.IGameService;
import com.marcoMario.iService.IUserService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Game;
import com.marcoMario.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestController {

    @Autowired
    private IUserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/registerUser")
    public String registerUser(@RequestParam("nameUser") String nameUser, @RequestParam("FirsPassword") String FirsPassword, @RequestParam("SecondPassword") String SecondPassword ) {
        return userService.registerUser(nameUser,FirsPassword, SecondPassword);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/logInUser")
    public String logInUser(@RequestParam("nameUser") String nameUser, @RequestParam("password") String password) {
        return userService.logInUser(nameUser,password);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewFavoriteGamesById/{page}")
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
