package com.crud.movies.domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int movieId;
    private String movieTitle;
    private String movieDescription;
    private double price;
    private Genre genre;
    private List<Rent> rents = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Movie(int movieId, String movieTitle, String movieDescription, double price) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.price = price;
    }

}
