package com.crud.movies.domain;

import com.crud.movies.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieTestSuite {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testMovieRepositorySave() {
        //Given
        Movie movie = new Movie();

        //When
        movieRepository.deleteAll();
        movieRepository.save(movie);

        //Then
        List<Movie> movies = movieRepository.findAll();
        assertEquals(1, movies.size());

        //CleanUp
        rentRepository.deleteAll();
    }

    @Test
    public void testMovieRepositoryDelete() {
        //Given
        Movie movie = new Movie();

        //When
        movieRepository.deleteAll();
        movieRepository.save(movie);
        movieRepository.delete(movie);

        //Then
        List<Movie> movies = movieRepository.findAll();
        assertEquals(0, movies.size());

        //CleanUp
        movieRepository.deleteAll();
    }

    @Test
    public void testCreateNewMovie() {
        //Given
        Movie movie = new Movie();

        //When
        movieRepository.save(movie);

        //Then
        int id = movie.getMovieId();
        Movie readMovie = movieRepository.findByMovieId(id);
        assertEquals(readMovie.getMovieId(),id);

        //CleanUp
        movieRepository.deleteById(id);
    }

}
