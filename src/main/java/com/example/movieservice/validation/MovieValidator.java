package com.example.movieservice.validation;

import com.example.movieservice.model.Omdb;
import com.example.movieservice.service.OmdbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MovieValidator implements ConstraintValidator<MovieValidation, String> {

    Logger logger = LoggerFactory.getLogger(MovieValidator.class);

    OmdbService omdbService;

    public MovieValidator(OmdbService omdbService) {
        this.omdbService = omdbService;
    }

    @Override
    public boolean isValid(String movieName, ConstraintValidatorContext context) {
        Omdb omdbMovie = omdbService.getOmdbMovie(movieName);
        if (omdbMovie.getResponse().equals("True")) {
            logger.info("{} was found on OMDB", movieName);
            return true;
        }
        logger.info("{} was not found on OMDB", movieName);
        return false;
    }
}