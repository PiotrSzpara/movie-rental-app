package com.crud.movies.controller;

import com.crud.movies.domain.MovieDto;
import com.crud.movies.facade.MovieFacade;
import com.crud.movies.facade.SearchException;
import com.crud.movies.mapper.MovieMapper;
import com.crud.movies.omdbapi.domain.MovieOmdb;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movie")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MovieController {

    private final MovieFacade movieFacade;

    private final MovieMapper movieMapper;

    @GetMapping("getAllMovies")
    List<MovieDto> getAllMovies() {
        return movieFacade.getAllMovies();
    }

    @GetMapping("getOmdbMovies")
    List<MovieOmdb> getOmdbMovies(@RequestParam String title) {
        return movieFacade.getOmdbMovies(title);
    }

    @GetMapping("getMoviesByGenre")
    public List<MovieDto> getMoviesByGenre(@RequestParam int genreId) {
        return movieFacade.getMoviesByGenre(genreId);
    }

    @GetMapping("getSingleMovies")
    public List<MovieDto> getSingleMovies() {
        return movieFacade.getSingleMovies();
    }

    @GetMapping("getSeries")
    public List<MovieDto> getSeries() {
        return movieFacade.getSeries();
    }

    @GetMapping("getKidsMovies")
    public List<MovieDto> getKidsMovies() {
        return movieFacade.getKidsMovies();
    }

    @GetMapping("getMovieById")
    public MovieDto getMovieById(@RequestParam int movieId) {
        return movieFacade.getMovieById(movieId);
    }

    @GetMapping("getMovieByTitle")
    public MovieDto getMovieByTitle(@RequestParam String movieTitle) {
        return movieFacade.getMovieByTitle(movieTitle);
    }

    @GetMapping("getMoviesByTitleFragment/{titleFragment}")
    public List<MovieDto> getMoviesByTitleFragment(@PathVariable String titleFragment) throws SearchException {
        return movieFacade.getMoviesByTitleFragment(titleFragment);
    }

    @PostMapping(value = "createMovie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMovie(@RequestBody MovieDto movieDto) {
        movieFacade.createMovie(movieDto);
    }

    @DeleteMapping("deleteMovie")
    public void deleteMovie(@RequestParam int movieId) {
       movieFacade.deleteMovie(movieId);
    }

    @PutMapping("updateMovie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) {
        return movieFacade.updateMovie(movieDto);
    }
}
