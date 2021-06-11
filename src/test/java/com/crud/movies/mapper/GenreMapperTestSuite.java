package com.crud.movies.mapper;

import com.crud.movies.domain.Genre;
import com.crud.movies.domain.GenreDto;
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
public class GenreMapperTestSuite {

    @Autowired
    private GenreMapper genreMapper;

    @Test
    public void testMapToGenre() {
        //Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");
        GenreDto genreDto = new GenreDto(1, "genreTypeTest", "genreDescriptionTest");

        //When
        Genre mappedGenre = genreMapper.mapToGenre(genreDto);

        //Then
        assertEquals(genreDto.getGenreId(),mappedGenre.getGenreId());
        assertEquals(genreDto.getGenreType(), mappedGenre.getGenreType());
        assertEquals(genreDto.getGenreDescription(), mappedGenre.getGenreDescription());
    }

    @Test
    public void testMapToGenreDto() {
        //Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");
        GenreDto genreDto = new GenreDto(1, "genreTypeTest", "genreDescriptionTest");

        //When
        GenreDto mappedGenre = genreMapper.mapToGenreDto(genre);

        //Then
        assertEquals(genre.getGenreId(),mappedGenre.getGenreId());
        assertEquals(genre.getGenreType(), mappedGenre.getGenreType());
        assertEquals(genre.getGenreDescription(), mappedGenre.getGenreDescription());
    }

    @Test
    public void testMapToGenreDtoList() {
        //Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");
        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre);

        GenreDto genreDto = new GenreDto(1, "genreTypeTest", "genreDescriptionTest");
        List<GenreDto> genreDtoList = new ArrayList<>();
        genreDtoList.add(genreDto);

        //When
        List<GenreDto> mappedGenreDtoList = genreMapper.mapToGenreDtoList(genreList);

        //Then
        assertEquals(genreDtoList.size(),mappedGenreDtoList.size());
        assertEquals(genreDtoList.get(0).getGenreId(),mappedGenreDtoList.get(0).getGenreId());
        assertEquals(genreDtoList.get(0).getGenreType(),mappedGenreDtoList.get(0).getGenreType());
        assertEquals(genreDtoList.get(0).getGenreDescription(),mappedGenreDtoList.get(0).getGenreDescription());
    }
}
