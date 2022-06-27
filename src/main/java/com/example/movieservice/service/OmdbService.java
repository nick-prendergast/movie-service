package com.example.movieservice.service;

import com.example.movieservice.model.Omdb;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;

@Service
public class OmdbService {

    @Value("${api-key}")
    private String apiKey;

    @Getter
    private Omdb existingOmdbMovie;


    boolean movieExists(String movieName) {
        Omdb omdbMovie;
        try {
            omdbMovie = getOmdbMovie(movieName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        if (omdbMovie.getResponse().equals("True")) {
            existingOmdbMovie = omdbMovie;
        }
        return omdbMovie.getResponse().equals("True");
    }

    private Omdb getOmdbMovie(String movieName) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("www.omdbapi.com").path("/").query("t={movieTitle}&apikey={apikey}").buildAndExpand(movieName, apiKey).encode();

        Omdb test = restTemplate.getForObject(uriComponents.toUri(), Omdb.class);
        return test;
    }
}
