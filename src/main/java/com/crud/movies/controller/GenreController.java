package com.crud.movies.controller;

import com.crud.movies.domain.GenreDto;
import com.crud.movies.facade.GenreFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/genre")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GenreController {

    private final GenreFacade genreFacade;

    @GetMapping("getAllGenres")
    public List<GenreDto> getAllGenres() {
        return genreFacade.getAllGenres();
    }

    @GetMapping("getGenreById")
    public GenreDto getGenreById(@RequestParam int genreId) {
        return genreFacade.getGenreById(genreId);
    }

    @PostMapping(value = "createGenre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGenre(@RequestBody GenreDto genreDto) {
       genreFacade.createGenre(genreDto);
    }

    @DeleteMapping("deleteGenre")
    public void deleteGenre(@RequestParam int genreId) {
        genreFacade.deleteGenre(genreId);
    }

    @PutMapping("updateGenre")
    public GenreDto updateGenre(@RequestBody GenreDto genreDto) {
        return genreFacade.updateGenre(genreDto);
    }
}
