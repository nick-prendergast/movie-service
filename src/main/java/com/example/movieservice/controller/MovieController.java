package com.example.movieservice.controller;

import com.example.movieservice.dto.RatingDto;
import com.example.movieservice.model.Rating;
import com.example.movieservice.service.MovieService;
import com.example.movieservice.validation.MovieValidation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    private final ModelMapper modelMapper;

    @GetMapping("/title/{movieName}")
    public String getMovie(@PathVariable @MovieValidation String movieName) {
        log.info("Started checking movie: {} for best picture award", movieName);
        return movieService.isBestPicture(movieName);
    }

    @PostMapping("/rating")
    public Rating rateMovie(@RequestBody @MovieValidation @Valid RatingDto ratingDto) {
        log.info("Started rating movie: {} ", ratingDto.getTitle());
        Rating rating = modelMapper.map(ratingDto, Rating.class);
        return movieService.rateMovie(rating);
    }

    @GetMapping("/rating/{findTopNMovies}")
    public List<Rating> getTopMovies(@PathVariable int findTopNMovies) {
        log.info("Getting top {} movies", findTopNMovies);
        return movieService.getTopMovies(findTopNMovies);
    }

}


