package com.marcoMario.restController;

import com.marcoMario.iService.IGameService;
import com.marcoMario.iService.IUserService;
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
}
