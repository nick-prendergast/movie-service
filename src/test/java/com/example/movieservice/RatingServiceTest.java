package com.example.movieservice;

import com.example.movieservice.model.Rating;
import com.example.movieservice.repository.RatingRepository;
import com.example.movieservice.service.OmdbService;
import com.example.movieservice.service.RatingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
class RatingServiceTest {

    @Autowired
    RatingRepository ratingRepository;
    RatingService ratingService;
    @Value("${api-key}")
    private String apiKey;

    @Value("${omdb-url}")
    private String omdbUrl;

    @BeforeEach
    void setUp() {
        OmdbService omdbService = new OmdbService(apiKey, omdbUrl);
        ratingService = new RatingService(ratingRepository, omdbService);
    }

    @Test
    void saveRating_Test() {
        Rating rating = new Rating();
        rating.setMovieRating(20);
        rating.setTitle("The Hurt Locker");
        ratingService.saveRating(rating);

        Optional<Rating> optionalRating = ratingRepository.findById(1L);
        if (optionalRating.isPresent()) {
            Rating ratingResult = optionalRating.get();
            Assertions.assertEquals("The Hurt Locker", ratingResult.getTitle());
            Assertions.assertEquals(20, ratingResult.getMovieRating());
            Assertions.assertEquals(new BigDecimal("17017811.00"), ratingResult.getBoxOffice());
        }
    }


}
