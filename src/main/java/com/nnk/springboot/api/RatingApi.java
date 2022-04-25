package com.nnk.springboot.api;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.payload.response.MessageResponse;
import com.nnk.springboot.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingApi {

    private RatingService ratingService;

    public RatingApi(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/rating")
    public ResponseEntity<List<Rating>> getRating() {
        List<Rating> ratings = ratingService.list();

        Logger.info("ratings listé !");

        return ResponseEntity.ok().body(ratings);

    }

    @PostMapping("/rating")
    public ResponseEntity<MessageResponse> createRating(@Valid @RequestBody Rating rating) {
        ratingService.save(rating);
        return ResponseEntity.ok(new MessageResponse("rating saved !"));
    }

    @PutMapping("/rating/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable("id") int id,@RequestBody Rating rating) throws  Exception{

        Rating result = ratingService.update(rating,id);

        Logger.info("La rating l'id n° "+id +" a été modifié !");

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/rating/{id}")
    public ResponseEntity<MessageResponse> deleteRating(@PathVariable int id) {
        ratingService.deleteById(id);

        Logger.warn("rating id "+id+" a été supprimé !");

        return ResponseEntity.ok(new MessageResponse("Rating id "+id+" a été supprimé !"));
    }
}
