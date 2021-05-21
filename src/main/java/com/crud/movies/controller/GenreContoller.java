package com.crud.movies.controller;

import com.crud.movies.domain.GenreDto;
import com.crud.movies.domain.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/genre")
public class GenreContoller {

    @RequestMapping(method = RequestMethod.GET, value = "getAllGenres")
    public List<GenreDto> getAllGenres() {
        return new ArrayList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGenre")
    public GenreDto getGenre(@RequestParam int genreId) {
        return new GenreDto(1);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGenre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGenre(@RequestBody GenreDto genreDto) {

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGenre")
    public void deleteGenre(@RequestParam int genreId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGenre")
    public void updateGenre(@RequestParam int genreId) {

    }

}
