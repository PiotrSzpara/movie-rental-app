package com.crud.movies.omdbapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreOmdb {
    @JsonProperty("genreId")
    private int genreId;
    @JsonProperty("genreType")
    private String genreType;
    @JsonProperty("genreDescription")
    private String genreDescription;
}
