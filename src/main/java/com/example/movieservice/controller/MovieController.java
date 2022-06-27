package com.example.movieservice.controller;

import com.example.movieservice.model.Rating;
import com.example.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/title/{movieName}")
    public String getMovie(@PathVariable String movieName) {
        return movieService.isBestPicture(movieName);
    }

    @PostMapping("/rating")
    public Rating rateMovie(@RequestBody Rating rating) {
        return movieService.rateMovie(rating);
    }

    @GetMapping("/rating")
    public List<Rating> getTopTenMovies() {
        return movieService.getTopTen();
    }

}


