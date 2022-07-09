package com.example.movieservice.validation;

import com.example.movieservice.dto.RatingDto;
import com.example.movieservice.model.OmdbMovieDto;
import com.example.movieservice.service.OmdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
public class RatingValidator implements ConstraintValidator<MovieValidation, RatingDto> {

    private final OmdbService omdbService;

    @Override
    public boolean isValid(RatingDto rating, ConstraintValidatorContext context) {
        OmdbMovieDto omdbMovieDtoMovie = omdbService.getOmdbMovie(rating.getTitle());
        if (omdbMovieDtoMovie.getResponse().equals("True")) {
            log.info("{} was found on OMDB", rating.getTitle());
            return true;
        }
        log.info("{} was not found on OMDB", rating.getTitle());
        return false;
    }
}
