package com.example.movieservice.controller;

import com.example.movieservice.dto.RatingDTO;
import com.example.movieservice.model.Rating;
import com.example.movieservice.service.MovieService;
import com.example.movieservice.validation.MovieValidation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class MovieController {

    Logger logger = LoggerFactory.getLogger(MovieController.class);

    MovieService movieService;

    ModelMapper modelMapper;

    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/title/{movieName}")
    public String getMovie(@PathVariable @MovieValidation String movieName) {
        logger.info("Started checking movie: {} for best picture award", movieName);
        return movieService.isBestPicture(movieName);
    }

    @PostMapping("/rating")
    public Rating rateMovie(@RequestBody @MovieValidation @Valid RatingDTO ratingDto) {
        logger.info("Started rating movie: {} ", ratingDto.getTitle());
        Rating rating = modelMapper.map(ratingDto, Rating.class);
        return movieService.rateMovie(rating);
    }

    @GetMapping("/rating")
    public List<Rating> getTopTenMovies() {
        logger.info("Getting top 10 movies");
        return movieService.getTopTen();
    }

}


