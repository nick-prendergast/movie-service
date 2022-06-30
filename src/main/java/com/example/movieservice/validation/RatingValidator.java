package com.example.movieservice.validation;

import com.example.movieservice.dto.RatingDTO;
import com.example.movieservice.model.Omdb;
import com.example.movieservice.service.OmdbService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<MovieValidation, RatingDTO> {

    OmdbService omdbService;

    public RatingValidator(OmdbService omdbService) {
        this.omdbService = omdbService;
    }

    @Override
    public boolean isValid(RatingDTO rating, ConstraintValidatorContext context) {
        Omdb omdbMovie = omdbService.getOmdbMovie(rating.getTitle());
        return omdbMovie.getResponse().equals("True");
    }

}
