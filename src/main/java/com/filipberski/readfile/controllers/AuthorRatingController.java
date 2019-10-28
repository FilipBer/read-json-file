package com.filipberski.readfile.controllers;


import com.filipberski.readfile.model.AuthorRating;
import com.filipberski.readfile.services.AuthorRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorRatingController {
    private AuthorRatingService authorRatingService;

    public AuthorRatingController(AuthorRatingService authorRatingService) {
        this.authorRatingService = authorRatingService;
    }

    @GetMapping("/rating")
    public ResponseEntity<List<AuthorRating>> getRating() {
        return new ResponseEntity<>(authorRatingService.getRating(), HttpStatus.OK);
    }
}
