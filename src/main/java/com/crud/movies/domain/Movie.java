package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIES")
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
