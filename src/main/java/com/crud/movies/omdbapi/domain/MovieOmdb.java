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
    @JsonProperty("Title")
    private String title = "";
    @JsonProperty("Year")
    private String year = "";
    @JsonProperty("imdbID")
    private String imdbID = "";
    @JsonProperty("Type")
    private String type = "";
    @JsonProperty("Poster")
    private String poster = "";
    @JsonProperty("Plot")
    private String shortplot = "";

}
