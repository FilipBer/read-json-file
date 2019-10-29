package com.filipberski.readfile.services;

import com.filipberski.readfile.model.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> getRating ();

    int findIdByAuthor(String author);
}
