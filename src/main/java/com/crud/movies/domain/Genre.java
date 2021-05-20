package com.crud.movies.domain;

import java.util.ArrayList;
import java.util.List;

public class Genre {

    private int genreId;
    private String genreType;
    private String genreDescription;
    private List<Movie> movies = new ArrayList<>();

    public Genre(int genreId, String genreType, String genreDescription) {
        this.genreId = genreId;
        this.genreType = genreType;
        this.genreDescription = genreDescription;
    }
}
