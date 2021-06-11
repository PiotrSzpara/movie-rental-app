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


    public List<MovieOmdb> getMoviesOmtb(String title) {
        try {
            MovieOmdb[] omdbResponse = restTemplate.getForObject(getUrl(title), MovieOmdb[].class);

            return Optional.ofNullable(omdbResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(m -> Objects.nonNull(m.getMovieTitle()))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public URI getUrl(String movieOmdbTitle) {
        return UriComponentsBuilder.fromHttpUrl(omdbApiConfig.getOmdbApiEndpoint())
                .queryParam("apikey", omdbApiConfig.getOmdbApiKey())
                .queryParam("s", movieOmdbTitle)
                .build()
                .encode()
                .toUri();

    }


}
