package com.example.movieservice;

import com.example.movieservice.model.AcademyAward;
import com.example.movieservice.service.CsvService;
import com.example.movieservice.service.OmdbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CsvServiceTest {

    CsvService csvService;

    @Value("${csv-file}")
    String csvFile;

    @Value("${api-key}")
    String apiKey;

    @BeforeEach
    void setup() {
        OmdbService omdbService = new OmdbService(apiKey);
        csvService = new CsvService(omdbService, csvFile);
    }

    @Test
    void testValueSetup() {
        assertNotNull(csvFile);
    }

    @Test
    void didMovieWinBestPictureAward_Test() {
        boolean result = csvService.didMovieWinBestPictureAward("The Hurt Locker");
        Assertions.assertTrue(result);
    }

    @Test
    void didMovieWinBestPictureAward_negative_Test() {
        boolean result = csvService.didMovieWinBestPictureAward("random movie");
        Assertions.assertFalse(result);
    }

    @Test
    void getOscarAwardsListFromCsv_Test() {
        List<AcademyAward> result = csvService.getOscarAwardsListFromCsv();
        Assertions.assertNotNull(result);
    }

}
