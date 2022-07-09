package com.example.movieservice.service;

import com.example.movieservice.model.Rating;
import com.example.movieservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final CsvService csvService;

    private final RatingService ratingService;

    private final RatingRepository ratingRepository;

    public String isBestPicture(String movieName) {
        if (csvService.didMovieWinBestPictureAward(movieName)) {
            return movieName + " did win best picture";
        }
        return "did not win best picture";
    }

    public Rating rateMovie(Rating movieRating) {
        return ratingService.saveRating(movieRating);
    }

    public List<Rating> getTopMovies(int findTopNMovies) {
        return ratingRepository.findTopMoviesByRatingOrderByBoxOffice(findTopNMovies);
    }
}

