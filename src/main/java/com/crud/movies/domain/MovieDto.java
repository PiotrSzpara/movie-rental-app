package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieDto {

    private int movieId;
    private String movieTitle;
    private String movieDescription;
    private double price;
}
