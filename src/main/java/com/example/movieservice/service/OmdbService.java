package com.example.movieservice.service;

import com.example.movieservice.configuration.ApiClient;
import com.example.movieservice.configuration.ApiKeyConfig;
import com.example.movieservice.model.OmdbMovieDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OmdbService {

    private final ApiClient apiClient;

    private ApiKeyConfig apiKeyConfig;

    public OmdbMovieDto getOmdbMovie(String movieName) {
        log.info("Checking OMDB API for {}", movieName);
        return apiClient.getOmdbMovieDto(movieName, apiKeyConfig.getApiKey());
    }


}
