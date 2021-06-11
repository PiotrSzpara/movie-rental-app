package com.crud.movies.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OmdbApiConfig {

    @Value("${omdb.api.search.endpoint}")
    private String omdbApiEndpoint;

    @Value("${omdb.api.key}")
    private String omdbApiKey;
}
