package com.example.movieservice.controller;

import com.example.movieservice.dto.RatingDTO;
import com.example.movieservice.model.Rating;
import com.example.movieservice.service.MovieService;
import com.example.movieservice.validation.MovieValidation;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class MovieController {

    MovieService movieService;

    ModelMapper modelMapper;

    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/title/{movieName}")
    public String getMovie(@PathVariable @MovieValidation String movieName) {
        return movieService.isBestPicture(movieName);
    }

    @PostMapping("/rating")
    public Rating rateMovie(@RequestBody @MovieValidation @Valid RatingDTO ratingDto) {
        Rating rating = modelMapper.map(ratingDto, Rating.class);
        return movieService.rateMovie(rating);
    }

    @GetMapping("/rating")
    public List<Rating> getTopTenMovies() {
        return movieService.getTopTen();
    }

}


