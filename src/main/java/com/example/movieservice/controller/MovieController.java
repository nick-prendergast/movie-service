package com.example.movieservice.controller;

import com.example.movieservice.model.Rating;
import com.example.movieservice.service.MovieService;
import com.example.movieservice.validation.MovieValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/title/{movieName}")
    public String getMovie(@PathVariable @MovieValidation String movieName) {
        return movieService.isBestPicture(movieName);
    }

    @PostMapping("/rating")
    public Rating rateMovie(@RequestBody @Valid Rating rating) {
        return movieService.rateMovie(rating);
    }

    @GetMapping("/rating")
    public List<Rating> getTopTenMovies() {
        return movieService.getTopTen();
    }

}


