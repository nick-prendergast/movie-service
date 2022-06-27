package com.example.movieservice.service;

import com.example.movieservice.model.AcademyAward;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CsvService {

    OmdbService omdbService;

    public CsvService(OmdbService omdbService) {
        this.omdbService = omdbService;
    }

    Boolean didMovieWinBestPictureAward(String movieName) {
        return getOscarAwardsListFromCsv(movieName).stream()
                .anyMatch(x -> x.didMovieWinBestPictureAward(movieName));
    }

    List<AcademyAward> getOscarAwardsListFromCsv(String movieName) {

        if (omdbService.movieExists(movieName)) {
            try {
                return new CsvToBeanBuilder(new FileReader("src/main/resources/academy_awards.csv"))
                        .withType(AcademyAward.class)
                        .build()
                        .parse();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException(movieName + " does not exist");
    }

}
