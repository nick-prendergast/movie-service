package com.example.movieservice;

import com.example.movieservice.model.Omdb;
import com.example.movieservice.service.OmdbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OmdbServiceTest {

    OmdbService omdbService;

    @Value("${api-key}")
    private String apiKey;

    @BeforeEach
    void setup() {
        omdbService = new OmdbService(apiKey);
    }


    @Test
    void getOmdbMovie_Test() {
        OmdbService omdbService = new OmdbService(apiKey);
        Omdb result = omdbService.getOmdbMovie("The Hurt Locker");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("The Hurt Locker", result.getTitle());
        Assertions.assertEquals("True", result.getResponse());
    }

    @Test
    void getOmdbMovie_invalidMovie_Test() {
        OmdbService omdbService = new OmdbService(apiKey);
        Omdb result = omdbService.getOmdbMovie("this movie doesn't exist");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("False",result.getResponse());
    }

}
