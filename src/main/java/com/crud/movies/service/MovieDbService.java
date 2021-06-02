package com.crud.movies.service;

import com.crud.movies.domain.Movie;
import com.crud.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDbService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById (final int movieId) {
        return movieRepository.findByMovieId(movieId);
    }

    public Movie getMovieByTitle (final String movieTitle) {
        return movieRepository.findByMovieTitle(movieTitle);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovieById(int movieId) {
        movieRepository.deleteById(movieId);
    }

}
