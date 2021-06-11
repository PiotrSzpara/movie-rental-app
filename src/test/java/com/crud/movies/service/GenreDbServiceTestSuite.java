package com.crud.movies.service;

import com.crud.movies.domain.Genre;
import com.crud.movies.repository.GenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class GenreDbServiceTestSuite {

    @InjectMocks
    private GenreDbService genreDbService;

    @Mock
    private GenreRepository genreRepository;


    @Test
    public void getAllGenresTest() {
        //Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");

        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre);

        when(genreRepository.findAll()).thenReturn(genreList);

        //When
        List<Genre> theList = genreDbService.getAllGenres();

        //Then
        assertEquals(1, theList.size());
        assertNotNull(theList);
    }
    @Test
    public void getGenreTest() {
        //Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");

        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre);

        when(genreRepository.findByGenreId(1)).thenReturn(genre);

        //When
        Genre theGenre = genreDbService.getGenre(1);

        //Then
        assertEquals(genre, theGenre);
    }

    @Test
    public void saveGenreTest() {
        //Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");

        when(genreRepository.save(genre)).thenReturn(genre);

        //When
        Genre savedGenre = genreDbService.saveGenre(genre);

        //Then
        assertEquals(genre.getGenreId(), savedGenre.getGenreId());
        assertEquals(genre.getGenreType(), savedGenre.getGenreType());
        assertEquals(genre.getGenreDescription(), savedGenre.getGenreDescription());
        assertEquals(genre.getMovies(), savedGenre.getMovies());
    }

    @Test
    public void deleteGenreTest() {
        //Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");

        int id = genre.getGenreId();

        genreDbService.deleteGenreById(id);

        //When
        Genre genreToDelete = genreDbService.getGenre(id);
        //Then
        assertNull(genreToDelete);

    }
}
