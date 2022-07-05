package com.example.movieservice.service;

import com.example.movieservice.model.AcademyAward;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CsvService {

    private final String csvFile;
    Logger logger = LoggerFactory.getLogger(CsvService.class);
    OmdbService omdbService;

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
            logger.info("querying csv file {}", csvFile);
            List<AcademyAward> csvList = new CsvToBeanBuilder(new FileReader(csvFile))
                    .withType(AcademyAward.class)
                    .build()
                    .parse();
            logger.info("csv generated AcademyAward list of size {} ", csvList.size());
            return csvList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
