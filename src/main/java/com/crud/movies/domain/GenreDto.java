package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GenreDto {

    private int genreId;
    private String genreType;
    private String genreDescription;

}
