package com.crud.movies.controller;

import com.crud.movies.domain.*;
import com.crud.movies.facade.GenreFacade;
import com.crud.movies.mapper.GenreMapper;
import com.crud.movies.service.GenreDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
public class GenreControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreDbService genreDbService;

    @MockBean
    private GenreFacade genreFacade;

    @MockBean
    private GenreMapper genreMapper;

    @Test
    public void getAllGenresEmptyTest() throws Exception {
        // Given
        List<GenreDto> genres = new ArrayList<>();
        when(genreFacade.getAllGenres()).thenReturn(genres);

        //When & Then
        mockMvc
                .perform(get("/v1/genre/getAllGenres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getAllGenresTest() throws Exception {
        // Given
        List<GenreDto> genres = new ArrayList<>();
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");
        GenreDto genreDto = new GenreDto(1, "genreTypeTest", "genreDescriptionTest");
        genres.add(genreDto);

        when(genreFacade.getAllGenres()).thenReturn(genres);

        //When & Then
        mockMvc
                .perform(get("/v1/genre/getAllGenres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
        ;
    }

    @Test
    public void getGenreTest() throws Exception {
        // Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");
        GenreDto genreDto = new GenreDto(1, "genreTypeTest", "genreDescriptionTest");

        when(genreFacade.getGenreById(1)).thenReturn(genreDto);
        when(genreMapper.mapToGenre(genreDto)).thenReturn(genre);

        //When & Then
        mockMvc
                .perform(get("/v1/genre/getGenreById?genreId=1")
                        .param("genreId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genreId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genreType", Matchers.is("genreTypeTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genreDescription", Matchers.is("genreDescriptionTest")))
                ;

    }

    @Test
    public void deleteGenreTest() throws Exception {
        // Given

        //When & Then
        mockMvc.perform(delete("/v1/genre/deleteGenre?genreId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createGenreTest() throws Exception {
        // Given
        Genre genre = new Genre(1, "genreTypeTest", "genreDescriptionTest");
        GenreDto genreDto = new GenreDto(1, "genreTypeTest", "genreDescriptionTest");

        when(genreDbService.saveGenre(any(Genre.class))).thenReturn(genre);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(genreDto);

        //When & Then
        mockMvc
                .perform(post("/v1/genre/createGenre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}
