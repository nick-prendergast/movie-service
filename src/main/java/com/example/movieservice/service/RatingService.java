package com.example.movieservice.service;

import com.example.movieservice.model.OmdbMovieDto;
import com.example.movieservice.model.Rating;
import com.example.movieservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    private final OmdbService omdbService;

    public Rating saveRating(Rating movieRating) {
        OmdbMovieDto omdbMovieDto = omdbService.getOmdbMovie(movieRating.getTitle());
        BigDecimal boxOffice = covertDollarStringToBigDecimal(omdbMovieDto.getBoxOffice());
        movieRating.setBoxOffice(boxOffice);
        log.info("saving movie rating to repo");
        return ratingRepository.save(movieRating);
    }

    private BigDecimal covertDollarStringToBigDecimal(String boxOffice) {
        BigDecimal moneyAmount = BigDecimal.ZERO;
        log.info("converting {} to BigDecimal", boxOffice);

        if (!boxOffice.equals("N/A")) {
            Locale locale = new Locale("en", "US");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            try {
                Number money = currencyFormat.parse(boxOffice);
                moneyAmount = BigDecimal.valueOf(money.doubleValue());
            } catch (ParseException pe) {
                log.error(pe.getMessage());
            }
        }
        return moneyAmount;
    }

}
