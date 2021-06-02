package com.crud.movies.controller;

import com.crud.movies.domain.GenreDto;
import com.crud.movies.facade.GenreFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/genre")
@RequiredArgsConstructor
public class GenreContoller {

    private GenreFacade genreFacade;

    @GetMapping(value = "getAllGenres")
    public List<GenreDto> getAllGenres() {
        return genreFacade.getAllGenres();
    }

    @GetMapping(value = "getGenre")
    public GenreDto getGenre(@RequestParam int genreId) {
        return genreFacade.getGenre(genreId);
    }

    @PostMapping(value = "createGenre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGenre(@RequestBody GenreDto genreDto) {
       genreFacade.createGenre(genreDto);
    }

    @DeleteMapping(value = "deleteGenre")
    public void deleteGenre(@RequestParam int genreId) {
        genreFacade.deleteGenre(genreId);
    }

    @PutMapping(value = "updateGenre")
    public GenreDto updateGenre(@RequestBody GenreDto genreDto) {
        return genreFacade.updateGenre(genreDto);
    }
}
