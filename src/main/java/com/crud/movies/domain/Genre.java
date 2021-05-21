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
@Table(name = "GENRES")
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
