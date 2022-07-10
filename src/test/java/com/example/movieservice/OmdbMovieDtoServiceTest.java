package com.example.movieservice;

import com.example.movieservice.configuration.ApiClient;
import com.example.movieservice.model.OmdbMovieDto;
import com.example.movieservice.service.OmdbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OmdbMovieDtoServiceTest {

    OmdbService omdbService;

    @Value("${api-key}")
    private String apiKey;

    @Value("${omdb-url}")
    private String omdbUrl;

    private ApiClient apiClient;


    @BeforeEach
    void setup() {
        omdbService = new OmdbService(apiKey, omdbUrl, apiClient);
    }


    @Test
    void getOmdbMovie_Test() {
//        OmdbService omdbService = new OmdbService(apiKey, omdbUrl);
        OmdbMovieDto result = omdbService.getOmdbMovie("The Hurt Locker");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("The Hurt Locker", result.getTitle());
        Assertions.assertEquals("True", result.getResponse());
    }

    @Test
    void getOmdbMovie_invalidMovie_Test() {
//        OmdbService omdbService = new OmdbService(apiKey, omdbUrl);
        OmdbMovieDto result = omdbService.getOmdbMovie("this movie doesn't exist");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("False", result.getResponse());
    }

}
