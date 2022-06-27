package com.example.movieservice.service;

import com.example.movieservice.model.Omdb;
import com.example.movieservice.model.Rating;
import com.example.movieservice.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    RatingRepository ratingRepository;

    OmdbService omdbService;

    public RatingService(OmdbService omdbService, RatingRepository ratingRepository) {
        this.omdbService = omdbService;
        this.ratingRepository = ratingRepository;
    }

    public Rating saveRating(Rating movieRating) {
        if (omdbService.movieExists(movieRating.getTitle())) {
            Omdb omdb = omdbService.getExistingOmdbMovie();
            movieRating.setBoxOffice(omdb.getBoxOffice());

            return ratingRepository.save(movieRating);
        }
        throw new RuntimeException(movieRating.getTitle() + " does not exist");

    }
}
