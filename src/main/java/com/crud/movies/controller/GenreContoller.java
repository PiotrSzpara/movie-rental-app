package com.crud.movies.controller;

import com.crud.movies.domain.Genre;
import com.crud.movies.domain.GenreDto;
import com.crud.movies.mapper.GenreMapper;
import com.crud.movies.service.GenreDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/genre")
public class GenreContoller {

    private final GenreDbService genreDbService;
    private final GenreMapper genreMapper;

    public GenreContoller(GenreDbService genreDbService, GenreMapper genreMapper) {
        this.genreDbService = genreDbService;
        this.genreMapper = genreMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllGenres")
    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreDbService.getAllGenres();
        return genreMapper.mapToGenreDtoList(genres);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGenre")
    public GenreDto getGenre(@RequestParam int genreId) {
        Genre genre = genreDbService.getGenre(genreId);
        return genreMapper.mapToGenreDto(genre);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGenre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGenre(@RequestBody GenreDto genreDto) {
        Genre newGenre = genreMapper.mapToGenre(genreDto);
        genreDbService.saveGenre(newGenre);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGenre")
    public void deleteGenre(@RequestParam int genreId) {
        genreDbService.deleteGenreById(genreId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGenre")
    public GenreDto updateGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreMapper.mapToGenre(genreDto);
        Genre updatedGenre = genreDbService.saveGenre(genre);
        return genreMapper.mapToGenreDto(updatedGenre);
    }
}
