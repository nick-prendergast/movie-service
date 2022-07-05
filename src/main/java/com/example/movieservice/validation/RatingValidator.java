package com.example.movieservice.validation;

import com.example.movieservice.dto.RatingDTO;
import com.example.movieservice.model.Omdb;
import com.example.movieservice.service.OmdbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<MovieValidation, RatingDTO> {

    Logger logger = LoggerFactory.getLogger(RatingValidator.class);

    OmdbService omdbService;

    public RatingValidator(OmdbService omdbService) {
        this.omdbService = omdbService;
    }

    @Override
    public boolean isValid(RatingDTO rating, ConstraintValidatorContext context) {
        Omdb omdbMovie = omdbService.getOmdbMovie(rating.getTitle());
        if (omdbMovie.getResponse().equals("True")) {
            logger.info("{} was found on OMDB", rating.getTitle());
            return true;
        }
        logger.info("{} was not found on OMDB", rating.getTitle());
        return false;
    }
}
