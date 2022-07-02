package com.example.movieservice.service;

import com.example.movieservice.model.AcademyAward;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CsvService {

    OmdbService omdbService;

    private final String csvFile;

    public CsvService(OmdbService omdbService, @Value("${csv-file}") String csvFile) {
        this.omdbService = omdbService;
        this.csvFile = csvFile;
    }

    public boolean didMovieWinBestPictureAward(String movieName) {
        return getOscarAwardsListFromCsv().stream()
                .anyMatch(x -> x.didMovieWinBestPictureAward(movieName));
    }

    public List<AcademyAward> getOscarAwardsListFromCsv() {
        try {
            return new CsvToBeanBuilder(new FileReader(csvFile))
                    .withType(AcademyAward.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
