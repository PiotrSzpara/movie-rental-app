package com.crud.movies.service;

import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieType;
import com.crud.movies.repository.MovieRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MovieDbServiceTestSuite {

    @InjectMocks
    private MovieDbService movieDbService;

    @Mock
    private MovieRepository movieRepository;


    @Test
    public void getAllMoviesTest() {
        //Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);


        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);

        when(movieRepository.findAll()).thenReturn(movieList);

        //When
        List<Movie> theList = movieDbService.getAllMovies();

        //Then
        assertEquals(1, theList.size());
        assertNotNull(theList);
    }
    @Test
    public void getMovieTest() {
        //Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);


        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);

        when(movieRepository.findByMovieId(1)).thenReturn(movie);

        //When
        Movie theMovie = movieDbService.getMovieById(1);

        //Then
                assertEquals(movie, theMovie);
    }

    @Test
    public void saveMovieTest() {
        //Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);

        when(movieRepository.save(movie)).thenReturn(movie);

        //When
        Movie savedMovie = movieDbService.saveMovie(movie);

        //Then
        assertEquals(movie.getMovieId(), savedMovie.getMovieId());
        assertEquals(movie.getMovieTitle(), savedMovie.getMovieTitle());
        assertEquals(movie.getMovieDescription(), savedMovie.getMovieDescription());
        assertEquals(movie.getMovieType(), savedMovie.getMovieType());
        assertEquals(movie.getPrice(), savedMovie.getPrice());
    }

    @Test
    public void deleteMovieTest() {
        //Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);

        int id = movie.getMovieId();

        movieDbService.deleteMovieById(id);

        //When
        Movie movieToDelete = movieDbService.getMovieById(id);
        //Then
        assertNull(movieToDelete);

    }
}
