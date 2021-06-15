package com.crud.movies.facade;


import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieDto;
import com.crud.movies.domain.MovieType;
import com.crud.movies.mapper.MovieMapper;
import com.crud.movies.omdbapi.client.OmdbApiClient;
import com.crud.movies.omdbapi.domain.MovieOmdb;
import com.crud.movies.repository.MovieRepository;
import com.crud.movies.service.GenreDbService;
import com.crud.movies.service.MovieDbService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MovieFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieFacade.class);

    private final MovieDbService movieDbService;
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final GenreDbService genreDbService;
    private final OmdbApiClient omdbApiClient;

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieDbService.getAllMovies();
        return movieMapper.mapToMovieDtoList(movies);
    }
    public MovieOmdb getOmdbMovie(@RequestParam String title) {
        return omdbApiClient.getMovieOmtb(title);

    }
    public List<MovieOmdb> getOmdbMovies(@RequestParam String title) {
        return omdbApiClient.getMoviesOmtb(title);
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

    public List<MovieDto> getMoviesByTitleFragment (@PathVariable String titleFragment) throws SearchException{
        if (titleFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_TITLE_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_TITLE_FRAGMENT_IS_NULL);
        }

        LOGGER.info("SEARCHING FOR MOVIES WITH TITLE CONTAINS: " + titleFragment);

        List<Movie> listOfMoviesFound = movieRepository.moviesWithTitle(titleFragment);
        if(listOfMoviesFound.isEmpty()) {
            LOGGER.info("NO MOVIES FOUND WHERE TITLE CONTAINS: " + titleFragment);
        }
        for(Movie movie : listOfMoviesFound) {
            LOGGER.info("MOVIE THAT MATCH THE CRITERIA: " + movie.getMovieTitle());
        }
        LOGGER.info("SEARCHING PROCESS ENDED");

        return movieMapper.mapToMovieDtoList(listOfMoviesFound);
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
