package com.example.movieservice.service;

import com.example.movieservice.model.Omdb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OmdbService {

    private final String apiKey;
    Logger logger = LoggerFactory.getLogger(OmdbService.class);

    public OmdbService(@Value("${api-key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public Omdb getOmdbMovie(String movieName) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("www.omdbapi.com").path("/").query("t={movieTitle}&apikey={apikey}").buildAndExpand(movieName, apiKey).encode();
        String uriString = uriComponents.toUriString();
        logger.info("Checking OMDB API for {} at {}", movieName, uriString);
        return restTemplate.getForObject(uriComponents.toUri(), Omdb.class);
    }
}
