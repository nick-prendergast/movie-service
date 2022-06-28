package com.example.movieservice.validation;

import com.example.movieservice.model.Omdb;
import com.example.movieservice.service.OmdbService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MovieValidator implements ConstraintValidator<MovieValidation, String> {

    OmdbService omdbService;

    public MovieValidator(OmdbService omdbService) {
        this.omdbService = omdbService;
    }

    @Override
    public boolean isValid(String movieName, ConstraintValidatorContext context) {
        Omdb omdbMovie = omdbService.getOmdbMovie(movieName);
        return omdbMovie.getResponse().equals("True");
    }
}