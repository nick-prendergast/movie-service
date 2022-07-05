package com.example.movieservice.service;

import com.example.movieservice.model.Omdb;
import com.example.movieservice.model.Rating;
import com.example.movieservice.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Service
public class RatingService {

    Logger logger = LoggerFactory.getLogger(RatingService.class);


    RatingRepository ratingRepository;

    OmdbService omdbService;

    public RatingService(OmdbService omdbService, RatingRepository ratingRepository) {
        this.omdbService = omdbService;
        this.ratingRepository = ratingRepository;
    }

    public Rating saveRating(Rating movieRating) {
        Omdb omdb = omdbService.getOmdbMovie(movieRating.getTitle());
        BigDecimal boxOffice = covertDollarStringToBigDecimal(omdb.getBoxOffice());
        movieRating.setBoxOffice(boxOffice);
        logger.info("saving movie rating to repo");
        return ratingRepository.save(movieRating);
    }

    private BigDecimal covertDollarStringToBigDecimal(String boxOffice) {
        BigDecimal moneyAmount = BigDecimal.ZERO;
        logger.info("converting {} to BigDecimal", boxOffice);

        if (!boxOffice.equals("N/A")) {
            Locale locale = new Locale("en", "US");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            try {
                Number money = currencyFormat.parse(boxOffice);
                moneyAmount = BigDecimal.valueOf(money.doubleValue());
            } catch (ParseException pe) {
                logger.error(pe.getMessage());
            }
        }
        return moneyAmount;
    }

}
