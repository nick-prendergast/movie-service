package com.example.movieservice.service;

import com.example.movieservice.model.Rating;
import com.example.movieservice.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    CsvService csvService;

    RatingService ratingService;

    RatingRepository ratingRepository;

    public MovieService(CsvService csvService, RatingService ratingService, RatingRepository ratingRepository) {
        this.csvService = csvService;
        this.ratingService = ratingService;
        this.ratingRepository = ratingRepository;
    }

    public String isBestPicture(String movieName) {
        if (csvService.didMovieWinBestPictureAward(movieName)) {
            return movieName + "did win best picture";
        }
        return "did not win best picture";
    }


    public Rating rateMovie(Rating movieRating) {
        return ratingService.saveRating(movieRating);
    }


    public List<Rating> getTopTen() {
        return ratingRepository.findTop10ByRatingOrderByBoxOffice();
    }
}

