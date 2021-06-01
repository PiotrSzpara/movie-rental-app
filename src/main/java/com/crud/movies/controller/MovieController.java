package com.crud.movies.controller;

import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieDto;
import com.crud.movies.domain.MovieType;
import com.crud.movies.facade.SearchingFacade;
import com.crud.movies.facade.SearchException;
import com.crud.movies.mapper.MovieMapper;
import com.crud.movies.service.GenreDbService;
import com.crud.movies.service.MovieDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    private final MovieDbService movieDbService;
    private final MovieMapper movieMapper;
    private final GenreDbService genreDbService;
    private final SearchingFacade searchingFacade;

    public MovieController(MovieDbService movieDbService, MovieMapper movieMapper, GenreDbService genreDbService, SearchingFacade searchingFacade) {
        this.movieDbService = movieDbService;
        this.movieMapper = movieMapper;
        this.genreDbService = genreDbService;
        this.searchingFacade = searchingFacade;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllMovies")
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieDbService.getAllMovies();
        return movieMapper.mapToMovieDtoList(movies);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMoviesByGenre")
    public List<MovieDto> getMoviesByGenre(@RequestParam int genreId) {
        List<Movie> moviesByGenre = genreDbService.getGenre(genreId).getMovies();
        return movieMapper.mapToMovieDtoList(moviesByGenre);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getSingleMovies")
    public List<MovieDto> getSingleMovies() {
        List<Movie> moviesByType = movieDbService.getAllMovies()
                .stream().filter(m -> m.getMovieType().equals(MovieType.SINGLE_MOVIE))
                .collect(Collectors.toList());
        return movieMapper.mapToMovieDtoList(moviesByType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getSeries")
    public List<MovieDto> getSeries() {
        List<Movie> moviesByType = movieDbService.getAllMovies()
                .stream().filter(m -> m.getMovieType().equals(MovieType.SERIES))
                .collect(Collectors.toList());
        return movieMapper.mapToMovieDtoList(moviesByType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getKidsMovies")
    public List<MovieDto> getKidsMovies() {
        List<Movie> moviesByType = movieDbService.getAllMovies()
                .stream().filter(m -> m.getMovieType().equals(MovieType.KIDS_MOVIE))
                .collect(Collectors.toList());
        return movieMapper.mapToMovieDtoList(moviesByType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMovieById")
    public MovieDto getMovieById(@RequestParam int movieId) {
        Movie movie = movieDbService.getMovieById(movieId);
        return movieMapper.mapToMovieDto(movie);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMovieByTitle")
    public MovieDto getMovieByTitle(@RequestParam String movieTitle) {
        Movie movie = movieDbService.getMovieByTitle(movieTitle);
        return movieMapper.mapToMovieDto(movie);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMoviesByTitleFragment")
    public List<MovieDto> getMoviesByTitleFragment(@RequestParam String titleFragment) throws SearchException {
        return movieMapper.mapToMovieDtoList(searchingFacade.moviesWithTitle(titleFragment));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMovie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMovie(@RequestBody MovieDto movieDto) {
        Movie newMovie = movieMapper.mapToMovie(movieDto);
        movieDbService.saveMovie(newMovie);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMovie")
    public void deleteMovie(@RequestParam int movieId) {
        movieDbService.deleteMovieById(movieId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateMovie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) {
        Movie movie = movieMapper.mapToMovie(movieDto);
        Movie updatedMovie = movieDbService.saveMovie(movie);
        return movieMapper.mapToMovieDto(updatedMovie);
    }
}
