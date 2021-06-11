package com.crud.movies.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "Movie.moviesWithTitle",
        query = "SELECT * FROM MOVIES WHERE MOVIE_TITLE LIKE CONCAT('%', :KEYWORD , '%')",
        resultClass = Movie.class)

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
    private MovieType movieType;
    private List<Rent> rents = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Movie(int movieId, String movieTitle, String movieDescription, MovieType movieType, double price) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieType = movieType;
        this.price = price;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "MOVIE_ID", unique = true)
    public int getMovieId() {
        return movieId;
    }

    @Column(name = "MOVIE_TITLE", length = 45)
    public String getMovieTitle() {
        return movieTitle;
    }

    @Column(name = "MOVIE_DESCRIPTION", length = 400)
    public String getMovieDescription() {
        return movieDescription;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    public Genre getGenre() {
        return genre;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_RENT_MOVIE",
            joinColumns = {@JoinColumn(name = "JOIN_MOVIE_ID", referencedColumnName = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "JOIN_RENT_ID", referencedColumnName = "RENT_ID")}
    )
    public List<Rent> getRents() {
        return rents;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "JOIN_ORDER_MOVIE",
            joinColumns = {@JoinColumn(name = "JOIN_MOVIE_ID", referencedColumnName = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "JOIN_ORDER_ID", referencedColumnName = "ORDER_ID")}
    )
    public List<Order> getOrders() {
        return orders;
    }


    @Column(name = "MOVIE_TYPE")
    public MovieType getMovieType() {
        return movieType;
    }

}
