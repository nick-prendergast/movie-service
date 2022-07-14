package com.example.movieservice.service;

import com.example.movieservice.configuration.ConfigProperties;
import com.example.movieservice.model.AcademyAward;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CsvService {

    private final ConfigProperties configProperties;

    public boolean didMovieWinBestPictureAward(String movieName) {
        return getOscarAwardsListFromCsv().stream()
                .anyMatch(x -> x.didMovieWinBestPictureAward(movieName));
    }

    public List<AcademyAward> getOscarAwardsListFromCsv() {
        try {
            log.info("querying csv file {}", configProperties.getCsvFile());
            List<AcademyAward> csvList = new CsvToBeanBuilder(new FileReader(configProperties.getCsvFile()))
                    .withType(AcademyAward.class)
                    .build()
                    .parse();
            log.info("csv generated AcademyAward list of size {} ", csvList.size());
            return csvList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
