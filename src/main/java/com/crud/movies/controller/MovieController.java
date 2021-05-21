package com.crud.movies.controller;

import com.crud.movies.domain.GenreDto;
import com.crud.movies.domain.MovieDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    @RequestMapping(method = RequestMethod.GET, value = "getAllMovies")
    public List<MovieDto> getAllMovies() {
        return new ArrayList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMovie")
    public MovieDto getMovie(@RequestParam int movieId) {
        return new MovieDto(1, "title", "description", 2.9);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMovie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMovie(@RequestBody MovieDto movieDto) {

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMovie")
    public void deleteMovie(@RequestParam int movieId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateMovie")
    public void updateMovie(@RequestParam int movieId) {

    }
}
