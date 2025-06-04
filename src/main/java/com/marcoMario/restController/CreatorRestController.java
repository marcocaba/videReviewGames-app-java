package com.marcoMario.restController;

import com.marcoMario.iService.ICreatorService;
import com.marcoMario.model.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CreatorRestController {

    @Autowired
    private ICreatorService creatorService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getCreatorsByGameId")
    public Set<Creator> getCreatorsByGameId(@RequestParam("idGame") long idGame) {
        return creatorService.getCreatorsByGameId(idGame);
    }
}
