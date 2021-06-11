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
public class GenreTestSuite {


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
    public void testGenreRepositorySave() {
        //Given
        Genre genre = new Genre();

        //When
        genreRepository.deleteAll();
        genreRepository.save(genre);

        //Then
        List<Genre> genres = genreRepository.findAll();
        assertEquals(1, genres.size());

        //CleanUp
        genreRepository.deleteAll();
    }

    @Test
    public void testGenreRepositoryDelete() {
        //Given
        Genre genre = new Genre();

        //When
        genreRepository.deleteAll();
        genreRepository.save(genre);
        genreRepository.delete(genre);

        //Then
        List<Genre> genres = genreRepository.findAll();
        assertEquals(0, genres.size());

        //CleanUp
        genreRepository.deleteAll();
    }
}
