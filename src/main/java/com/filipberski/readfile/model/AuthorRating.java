package com.filipberski.readfile.model;

public class AuthorRating {
    private String name;
    private Double ratingSum;
    private Integer numberOfRatings;
    private Double averageRating;

    public AuthorRating(String name, Double ratingSum) {
        this.name = name;
        this.ratingSum = ratingSum;
        this.numberOfRatings = 1;
        this.averageRating = this.ratingSum / this.numberOfRatings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(Double ratingSum) {
        this.ratingSum = ratingSum;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void addRating(Double rating) {
        this.ratingSum += rating;
        this.numberOfRatings++;
        this.averageRating = this.ratingSum / numberOfRatings;
    }
}
