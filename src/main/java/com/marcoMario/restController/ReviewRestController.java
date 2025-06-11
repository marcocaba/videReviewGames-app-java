package com.marcoMario.restController;

import com.marcoMario.iService.IReviewService;
import com.marcoMario.model.DTO.GameDTO;
import com.marcoMario.model.DTO.ObjectPage;
import com.marcoMario.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewRestController {

    @Autowired
    private IReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewReviewsUser")
    public ObjectPage viewReviewsUser(@RequestParam("page") int page ) {
        return reviewService.getReviewsUser(page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addReviewUser")
    public String addReviewUser(@RequestBody Review review) {
        return reviewService.addReviewUser(review);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/updateReview")
    public boolean updateReview(@RequestBody Review review) {
        return reviewService.updateReview(review);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/removeReviewUser")
    public String removeReviewUser(@RequestParam("idGame") long idGame) {
        return reviewService.removeReviewUser(idGame);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/viewReviewsByGame")
    public ObjectPage viewReviewsByGame( @RequestParam("idGame") long idGame, @RequestParam("page") int page ) {
        return reviewService.getReviewsByGame(idGame, page);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/calculateNote")
    public double calculateNote( @RequestParam("idGame") long idGame) {
        System.out.println(reviewService.calculateNote(idGame));
        return reviewService.calculateNote(idGame);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getFourBestGames")
    public List<GameDTO> getFourBestGames() {
        return reviewService.getFourBestGames();
    }

}
