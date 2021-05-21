package com.crud.movies.mapper;

import com.crud.movies.domain.Genre;
import com.crud.movies.domain.GenreDto;
import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieMapper {

    public Movie mapToMovie(final MovieDto movieDto) {
        return new Movie(
                movieDto.getMovieId(),
                movieDto.getMovieTitle(),
                movieDto.getMovieDescription(),
                movieDto.getPrice()
        );
    }

    public MovieDto mapToMovieDto(final Movie movie) {
        return new MovieDto(
                movie.getMovieId(),
                movie.getMovieTitle(),
                movie.getMovieDescription(),
                movie.getPrice()
        );
    }

    public List<MovieDto> mapToMovieDtoList(final List<Movie> movies) {
        return movies.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());
    }
}
