package com.example.movieservice.service;

import com.example.movieservice.model.AcademyAward;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
@Slf4j
//@RequiredArgsConstructor
public class CsvService {

    private final String csvFile;

    public CsvService( @Value("${csv-file}") String csvFile) {
        this.csvFile = csvFile;
    }

    public boolean didMovieWinBestPictureAward(String movieName) {
        return getOscarAwardsListFromCsv().stream()
                .anyMatch(x -> x.didMovieWinBestPictureAward(movieName));
    }

    public List<AcademyAward> getOscarAwardsListFromCsv() {
        try {
            log.info("querying csv file {}", csvFile);
            List<AcademyAward> csvList = new CsvToBeanBuilder(new FileReader(csvFile))
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
