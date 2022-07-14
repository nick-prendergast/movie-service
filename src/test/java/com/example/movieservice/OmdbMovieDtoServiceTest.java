package com.example.movieservice;

import com.example.movieservice.configuration.ApiClient;
import com.example.movieservice.configuration.ApiKeyConfig;
import com.example.movieservice.model.OmdbMovieDto;
import com.example.movieservice.service.OmdbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OmdbMovieDtoServiceTest {

    OmdbService omdbService;

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private ApiKeyConfig apiKeyConfig;

    @BeforeEach
    void setup() {
        apiKeyConfig.setApiKey("2a0ebc3f");
        omdbService = new OmdbService(apiClient, apiKeyConfig);
    }


    @Test
    void getOmdbMovie_Test() {
        OmdbMovieDto result = omdbService.getOmdbMovie("The Hurt Locker");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("The Hurt Locker", result.getTitle());
        Assertions.assertEquals("True", result.getResponse());
    }

    @Test
    void getOmdbMovie_invalidMovie_Test() {
        OmdbMovieDto result = omdbService.getOmdbMovie("this movie doesn't exist");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("False", result.getResponse());
    }

}
