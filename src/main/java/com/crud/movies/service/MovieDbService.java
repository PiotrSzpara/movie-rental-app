package com.crud.movies.service;

import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDbService {
    @Autowired
    private final MovieDao movieDao;

    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    public Movie getMovieById (final int movieId) {
        return movieDao.findById(movieId);
    }

    public Movie getMovieByTitle (final String movieTitle) {
        return movieDao.findByTitle(movieTitle);
    }

    public Movie saveMovie(Movie movie) {
        return movieDao.save(movie);
    }

    public void deleteMovieById(int movieId) {
        movieDao.deleteById(movieId);
    }

}
