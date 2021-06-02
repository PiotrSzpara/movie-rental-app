package com.crud.movies.facade;


import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieDto;
import com.crud.movies.domain.MovieType;
import com.crud.movies.mapper.MovieMapper;
import com.crud.movies.service.GenreDbService;
import com.crud.movies.service.MovieDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MovieFacade {

    private final MovieDbService movieDbService;
    private final MovieMapper movieMapper;
    private final GenreDbService genreDbService;
    private final SearchingFacade searchingFacade;

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieDbService.getAllMovies();
        return movieMapper.mapToMovieDtoList(movies);
    }

    public List<MovieDto> getMoviesByGenre(@RequestParam int genreId) {
        List<Movie> moviesByGenre = genreDbService.getGenre(genreId).getMovies();
        return movieMapper.mapToMovieDtoList(moviesByGenre);
    }

    public List<MovieDto> getSingleMovies() {
        List<Movie> moviesByType = movieDbService.getAllMovies()
                .stream().filter(m -> m.getMovieType().equals(MovieType.SINGLE_MOVIE))
                .collect(Collectors.toList());
        return movieMapper.mapToMovieDtoList(moviesByType);
    }

    public List<MovieDto> getSeries() {
        List<Movie> moviesByType = movieDbService.getAllMovies()
                .stream().filter(m -> m.getMovieType().equals(MovieType.SERIES))
                .collect(Collectors.toList());
        return movieMapper.mapToMovieDtoList(moviesByType);
    }

    public List<MovieDto> getKidsMovies() {
        List<Movie> moviesByType = movieDbService.getAllMovies()
                .stream().filter(m -> m.getMovieType().equals(MovieType.KIDS_MOVIE))
                .collect(Collectors.toList());
        return movieMapper.mapToMovieDtoList(moviesByType);
    }

    public MovieDto getMovieById(@RequestParam int movieId) {
        Movie movie = movieDbService.getMovieById(movieId);
        return movieMapper.mapToMovieDto(movie);
    }

    public MovieDto getMovieByTitle(@RequestParam String movieTitle) {
        Movie movie = movieDbService.getMovieByTitle(movieTitle);
        return movieMapper.mapToMovieDto(movie);
    }

    public List<MovieDto> getMoviesByTitleFragment(@RequestParam String titleFragment) throws SearchException {
        return movieMapper.mapToMovieDtoList(searchingFacade.moviesWithTitle(titleFragment));
    }

    public void createMovie(@RequestBody MovieDto movieDto) {
        Movie newMovie = movieMapper.mapToMovie(movieDto);
        movieDbService.saveMovie(newMovie);
    }

    public void deleteMovie(@RequestParam int movieId) {
        movieDbService.deleteMovieById(movieId);
    }

    public MovieDto updateMovie(@RequestBody MovieDto movieDto) {
        Movie movie = movieMapper.mapToMovie(movieDto);
        Movie updatedMovie = movieDbService.saveMovie(movie);
        return movieMapper.mapToMovieDto(updatedMovie);
    }
}
