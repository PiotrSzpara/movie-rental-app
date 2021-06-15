package com.crud.movies.omdbapi.client;

import com.crud.movies.config.OmdbApiConfig;
import com.crud.movies.omdbapi.domain.MovieOmdb;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OmdbApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmdbApiClient.class);
    private final RestTemplate restTemplate;
    private final OmdbApiConfig omdbApiConfig;


    public MovieOmdb getMovieOmtb(String title) {
        return restTemplate.getForObject(getUrlT(title), MovieOmdb.class);
    }
    public List<MovieOmdb> getMoviesOmtb(String title) {

        try{
            MovieOmdb[] omdbResponse = restTemplate.getForObject(getUrlS(title), MovieOmdb[].class);
            return Optional.ofNullable(omdbResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(m -> Objects.nonNull(m.getTitle()))
                .collect(Collectors.toList());
        }catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }


    public URI getUrlS(String title) {
        return UriComponentsBuilder.fromHttpUrl(omdbApiConfig.getOmdbApiEndpoint())
                .queryParam("apikey", omdbApiConfig.getOmdbApiKey())
                .queryParam("s", title)
                .build()
                .encode()
                .toUri();

    }
    public URI getUrlT(String title) {
        return UriComponentsBuilder.fromHttpUrl(omdbApiConfig.getOmdbApiEndpoint())
                .queryParam("apikey", omdbApiConfig.getOmdbApiKey())
                .queryParam("t", title)
                .build()
                .encode()
                .toUri();

    }


}
