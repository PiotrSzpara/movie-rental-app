package com.crud.movies.controller;

import com.crud.movies.domain.*;
import com.crud.movies.facade.MovieFacade;
import com.crud.movies.mapper.MovieMapper;
import com.crud.movies.repository.MovieRepository;
import com.crud.movies.service.MovieDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.hibernate.cfg.PkDrivenByDefaultMapsIdSecondPass;
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
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieDbService movieDbService;

    @MockBean
    private MovieFacade movieFacade;

    @MockBean
    private MovieMapper movieMapper;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void getAllMoviesEmptyTest() throws Exception {
        // Given
        List<MovieDto> movies = new ArrayList<>();
        when(movieFacade.getAllMovies()).thenReturn(movies);

        //When & Then
        mockMvc
                .perform(get("/v1/movie/getAllMovies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getAllMoviesTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);

        List<MovieDto> movies = new ArrayList<>();
        movies.add(movieDto);
        when(movieFacade.getAllMovies()).thenReturn(movies);

        //When & Then
        mockMvc
                .perform(get("/v1/movie/getAllMovies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void getMovieTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);


        when(movieFacade.getMovieById(1)).thenReturn(movieDto);
        when(movieMapper.mapToMovie(movieDto)).thenReturn(movie);

        //When & Then
        mockMvc
                .perform(get("/v1/movie/getMovieById?movieId=1")
                        .param("movieId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieTitle", Matchers.is("movieTitleTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieDescription", Matchers.is("movieDescriptionTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieType", Matchers.is("KIDS_MOVIE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(20.0)))
        ;

    }

    @Test
    public void getMovieByTitleTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);


        when(movieFacade.getMovieByTitle("movieTitleTest")).thenReturn(movieDto);
        when(movieMapper.mapToMovie(movieDto)).thenReturn(movie);

        //When & Then
        mockMvc
                .perform(get("/v1/movie/getMovieByTitle")
                        .param("movieTitle", "movieTitleTest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieTitle", Matchers.is("movieTitleTest")))
        ;
    }

    @Test
    public void getMoviesByTitleFragmentTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        List<MovieDto> moviesDto = new ArrayList<>();
        moviesDto.add(movieDto);

        List<MovieDto> filteredMoviesDto = moviesDto
                .stream()
                .filter(m -> m.getMovieTitle().contains("tle"))
                .collect(Collectors.toList());

        when(movieFacade.getMoviesByTitleFragment("tle")).thenReturn(filteredMoviesDto);

        //When & Then
        mockMvc
                .perform(get("/v1/movie/getMoviesByTitleFragment/tle")
                        //.param("movieTitle", "tle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
        ;
    }

    @Test
    public void getKidsMovieTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        List<MovieDto> movies = new ArrayList<>();
        movies.add(movieDto);
        List<MovieDto> kidsMovies = movies.stream().filter(m -> m.getMovieType().equals(MovieType.KIDS_MOVIE))
                .collect(Collectors.toList());
        when(movieFacade.getKidsMovies()).thenReturn(kidsMovies);


        //When & Then
        mockMvc
                .perform(get("/v1/movie/getKidsMovies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void getSingleMovieTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.SINGLE_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.SINGLE_MOVIE,20.0);
        List<MovieDto> movies = new ArrayList<>();
        movies.add(movieDto);
        List<MovieDto> singleMovies = movies.stream().filter(m -> m.getMovieType().equals(MovieType.SINGLE_MOVIE))
                .collect(Collectors.toList());
        when(movieFacade.getSingleMovies()).thenReturn(singleMovies);


        //When & Then
        mockMvc
                .perform(get("/v1/movie/getSingleMovies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void getSeriesTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.SERIES,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.SERIES,20.0);
        List<MovieDto> movies = new ArrayList<>();
        movies.add(movieDto);
        List<MovieDto> series = movies.stream().filter(m -> m.getMovieType().equals(MovieType.SERIES))
                .collect(Collectors.toList());
        when(movieFacade.getSeries()).thenReturn(series);


        //When & Then
        mockMvc
                .perform(get("/v1/movie/getSeries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void deleteMovieTest() throws Exception {
        // Given

        //When & Then
        mockMvc.perform(delete("/v1/movie/deleteMovie?movieId=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createMovieTest() throws Exception {
        // Given
        Movie movie = new Movie(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);
        MovieDto movieDto = new MovieDto(1, "movieTitleTest", "movieDescriptionTest", MovieType.KIDS_MOVIE,20.0);

        when(movieDbService.saveMovie(any(Movie.class))).thenReturn(movie);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(movieDto);

        //When & Then
        mockMvc
                .perform(post("/v1/movie/createMovie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}
