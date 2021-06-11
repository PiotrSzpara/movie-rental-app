package com.crud.movies.omdbapi.domain;

import com.crud.movies.domain.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieOmdb {
    @JsonProperty("movieId")
    private int movieId;
    @JsonProperty("movieTitle")
    private String movieTitle;
    @JsonProperty("movieDescription")
    private String movieDescription;

}
