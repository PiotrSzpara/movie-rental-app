package com.crud.movies.mapper;

import com.crud.movies.domain.Genre;
import com.crud.movies.domain.GenreDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreMapper {

    public Genre mapToGenre(final GenreDto genreDto) {
        return new Genre(
                genreDto.getGenreId(),
                genreDto.getGenreType(),
                genreDto.getGenreDescription()
        );
    }

    public GenreDto mapToGenreDto(final Genre genre) {
        return new GenreDto(
                genre.getGenreId(),
                genre.getGenreType(),
                genre.getGenreDescription()
        );
    }

    public List<GenreDto> mapToGenreDtoList(final List<Genre> genres) {
        return genres.stream()
                .map(this::mapToGenreDto)
                .collect(Collectors.toList());
    }
}
