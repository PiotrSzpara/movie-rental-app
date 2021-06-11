package com.crud.movies.mapper;

import com.crud.movies.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieMapperTestSuite {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void testMapToMovie() {
        //Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        //When
        Movie mappedMovie = movieMapper.mapToMovie(movieDto);

        //Then
        assertEquals(movieDto.getMovieId(),mappedMovie.getMovieId());
        assertEquals(movieDto.getMovieTitle(), mappedMovie.getMovieTitle());
        assertEquals(movieDto.getMovieDescription(), mappedMovie.getMovieDescription());
        assertEquals(movieDto.getMovieType(), mappedMovie.getMovieType());
        assertTrue(movieDto.getPrice() == mappedMovie.getPrice());
    }

    @Test
    public void testMapToMovieDto() {
        //Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);

        //When
        MovieDto mappedMovie = movieMapper.mapToMovieDto(movie);

        //Then
        assertEquals(movie.getMovieId(),mappedMovie.getMovieId());
        assertEquals(movie.getMovieTitle(), mappedMovie.getMovieTitle());
        assertEquals(movie.getMovieDescription(), mappedMovie.getMovieDescription());
        assertEquals(movie.getMovieType(), mappedMovie.getMovieType());
        assertTrue(movie.getPrice() == mappedMovie.getPrice());
    }

    @Test
    public void testMapToMovieDtoList() {
        //Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);

        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(movieDto);

        //When
        List<MovieDto> mappedMovieDtoList = movieMapper.mapToMovieDtoList(movieList);

        //Then
        assertEquals(movieDtoList.size(),mappedMovieDtoList.size());
        assertEquals(movieDtoList.get(0).getMovieId(),mappedMovieDtoList.get(0).getMovieId());
        assertEquals(movieDtoList.get(0).getMovieTitle(),mappedMovieDtoList.get(0).getMovieTitle());
        assertEquals(movieDtoList.get(0).getMovieDescription(),mappedMovieDtoList.get(0).getMovieDescription());
        assertEquals(movieDtoList.get(0).getMovieType(),mappedMovieDtoList.get(0).getMovieType());
        assertTrue(movieDtoList.get(0).getPrice() == mappedMovieDtoList.get(0).getPrice());
    }
}
