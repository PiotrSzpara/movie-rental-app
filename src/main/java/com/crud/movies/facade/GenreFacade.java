package com.crud.movies.facade;

import com.crud.movies.domain.Genre;
import com.crud.movies.domain.GenreDto;
import com.crud.movies.mapper.GenreMapper;
import com.crud.movies.service.GenreDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class GenreFacade {

    private final GenreDbService genreDbService;
    private final GenreMapper genreMapper;

    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreDbService.getAllGenres();
        return genreMapper.mapToGenreDtoList(genres);
    }

    public GenreDto getGenre(@RequestParam int genreId) {
        Genre genre = genreDbService.getGenre(genreId);
        return genreMapper.mapToGenreDto(genre);
    }

    public void createGenre(@RequestBody GenreDto genreDto) {
        Genre newGenre = genreMapper.mapToGenre(genreDto);
        genreDbService.saveGenre(newGenre);
    }

    public void deleteGenre(@RequestParam int genreId) {
        genreDbService.deleteGenreById(genreId);
    }

    public GenreDto updateGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreMapper.mapToGenre(genreDto);
        Genre updatedGenre = genreDbService.saveGenre(genre);
        return genreMapper.mapToGenreDto(updatedGenre);
    }
}
