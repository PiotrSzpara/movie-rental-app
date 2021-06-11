package com.crud.movies.omdbapi;

import com.crud.movies.config.OmdbApiConfig;
import com.crud.movies.omdbapi.client.OmdbApiClient;
import com.crud.movies.omdbapi.domain.MovieOmdb;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class OmdbApiTestSuite {
    @InjectMocks
    private OmdbApiClient omdbApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OmdbApiConfig omdbApiConfig;

    @Mock
    private MovieOmdb movieOmdb;

    @Test
    public void getOmdbMoviesTest() throws URISyntaxException {
        //Given
        when(omdbApiConfig.getOmdbApiEndpoint()).thenReturn("http://www.omdbapi.com/");
        when(omdbApiConfig.getOmdbApiKey()).thenReturn("e033b4d3");
        when(movieOmdb.getMovieTitle()).thenReturn("ozark");


        MovieOmdb[] movies = new MovieOmdb[1];
        movies[0] = new MovieOmdb(1, "Ozark","bla");

        URI uri = new URI("http://www.omdbapi.com/?apikey=e033b4d3&s=ozark");

        when(restTemplate.getForObject(uri, MovieOmdb[].class)).thenReturn(movies);

        //When
        List<MovieOmdb> moviesOmdb = omdbApiClient.getMoviesOmtb("ozark");
        System.out.println(moviesOmdb);

        //Then
        assertEquals(1, moviesOmdb.size());
        assertEquals(1, moviesOmdb.get(0).getMovieId());
        assertEquals("Ozark", moviesOmdb.get(0).getMovieTitle());
        assertEquals(uri, omdbApiClient.getUrl("ozark"));
    }
}
