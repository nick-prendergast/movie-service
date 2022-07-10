package com.example.movieservice.service;

import com.example.movieservice.configuration.ApiClient;
import com.example.movieservice.model.OmdbMovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OmdbService {

    private final String apiKey;

    private final String omdbUrl;

    @Autowired
    private final ApiClient apiClient;



    public OmdbService(@Value("${api-key}") String apiKey, @Value("${omdb-url}") String omdbUrl, ApiClient apiClient) {
        this.apiKey = apiKey;
        this.omdbUrl = omdbUrl;
        this.apiClient = apiClient;
    }

    public OmdbMovieDto getOmdbMovie(String movieName) {
        log.info("Checking OMDB API for {}", movieName);
        OmdbMovieDto result = apiClient.getOmdbMovieDto(movieName, apiKey);
        return result;
    }


}
