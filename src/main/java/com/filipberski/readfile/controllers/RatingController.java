package com.filipberski.readfile.controllers;


import com.filipberski.readfile.model.Rating;
import com.filipberski.readfile.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/rating")
    public ResponseEntity<List<Rating>> getRating() {
        return new ResponseEntity<>(ratingService.getRating(), HttpStatus.OK);
    }
}
