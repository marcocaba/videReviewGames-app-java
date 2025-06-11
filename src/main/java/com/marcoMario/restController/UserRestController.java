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
    public String registerUser(@RequestParam("nameUser") String nameUser, @RequestParam("firstPassword") String firstPassword, @RequestParam("secondPassword") String secondPassword ) {
        return userService.registerUser(nameUser,firstPassword, secondPassword);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/checkLogInState")
    public boolean checkLogInState() {
        return userService.checkLogInState();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/logInUser")
    public String logInUser(@RequestParam("nameUser") String nameUser, @RequestParam("password") String password) {
        System.out.println("iniciada");
        return userService.logInUser(nameUser,password);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/logOutUser")
    public boolean logOutUser() {
        System.out.println("cerrada");
        return userService.logOutUser();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewFavoriteGamesById/{page}")
    public ObjectPage viewFavoriteGamesById( @PathVariable("page") int page ) {
        return userService.viewFavoriteGamesById(page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addGameToFavorites" )
    public String addGameToFavorites( @RequestParam("gameId") long gameId ) {
        return userService.addGameToFavorites(gameId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/removeGameFromFavorites")
    public String removeGameFromFavorites( @RequestParam("gameId") long gameId ) {
        return userService.removeGameFromFavorites(gameId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getNameUserByIdUser")
    public String getNameUserByIdUser() {
        return userService.getNameUserByIdUser();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }
}
