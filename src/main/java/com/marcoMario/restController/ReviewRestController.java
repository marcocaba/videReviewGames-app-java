package com.marcoMario.restController;

import com.marcoMario.iService.IReviewService;
import com.marcoMario.model.DTO.ObjectPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewRestController {

    @Autowired
    private IReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewReviewsUser")
    public ObjectPage viewReviewsUser(@RequestParam("idUser") long idUser, @RequestParam("page") int page ) {
        return reviewService.getReviewsUser(idUser, page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addReviewUser")
    public String addReviewUser(@RequestParam("idUser") long idUser, @RequestParam("idGame") long idGame, @RequestParam("text") String text, @RequestParam("score") int score ) {
        return reviewService.addReviewUser(idUser, idGame, text, score);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/removeReviewUser")
    public String removeReviewUser(@RequestParam("idUser") long idUser, @RequestParam("idGame") long idGame) {
        return reviewService.removeReviewUser(idUser, idGame);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewReviewsByGame")
    public ObjectPage viewReviewsByGame( @RequestParam("idGame") long idGame, @RequestParam("page") int page ) {
        return reviewService.getReviewsByGame(idGame, page);
    }

}
