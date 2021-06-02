package com.crud.movies.controller;

import com.crud.movies.domain.MovieDto;
import com.crud.movies.facade.MovieFacade;
import com.crud.movies.facade.SearchException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private MovieFacade movieFacade;

    @GetMapping(value = "getAllMovies")
    List<MovieDto> getAllMovies() {
        return movieFacade.getAllMovies();
    }

    @GetMapping(value = "getMoviesByGenre")
    public List<MovieDto> getMoviesByGenre(@RequestParam int genreId) {
        return movieFacade.getMoviesByGenre(genreId);
    }

    @GetMapping(value = "getSingleMovies")
    public List<MovieDto> getSingleMovies() {
        return movieFacade.getSingleMovies();
    }

    @GetMapping(value = "getSeries")
    public List<MovieDto> getSeries() {
        return movieFacade.getSeries();
    }

    @GetMapping(value = "getKidsMovies")
    public List<MovieDto> getKidsMovies() {
        return movieFacade.getKidsMovies();
    }

    @GetMapping(value = "getMovieById")
    public MovieDto getMovieById(@RequestParam int movieId) {
        return movieFacade.getMovieById(movieId);
    }

    @GetMapping(value = "getMovieByTitle")
    public MovieDto getMovieByTitle(@RequestParam String movieTitle) {
        return movieFacade.getMovieByTitle(movieTitle);
    }

    @GetMapping(value = "getMoviesByTitleFragment")
    public List<MovieDto> getMoviesByTitleFragment(@RequestParam String titleFragment) throws SearchException {
        return movieFacade.getMoviesByTitleFragment(titleFragment);
    }

    @PostMapping(value = "createMovie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMovie(@RequestBody MovieDto movieDto) {
        movieFacade.createMovie(movieDto);
    }

    @DeleteMapping(value = "deleteMovie")
    public void deleteMovie(@RequestParam int movieId) {
       movieFacade.deleteMovie(movieId);
    }

    @PutMapping(value = "updateMovie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) {
        return movieFacade.updateMovie(movieDto);
    }
}
