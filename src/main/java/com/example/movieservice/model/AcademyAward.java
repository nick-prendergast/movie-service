package com.example.movieservice.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class AcademyAward {

    @CsvBindByName(column = "Year")
    private String year;

    @CsvBindByName(column = "Category")
    private String category;

    @CsvBindByName(column = "Nominee")
    private String nominee;

    @CsvBindByName(column = "Additional")
    private String additional;

    @CsvBindByName(column = "Info")
    private String info;

    @CsvBindByName(column = "Won?")
    private String won;

    public boolean didMovieWinBestPictureAward(String movieName) {
        return nominee.equals(movieName) && category.equals("Best Picture") && won.equals("YES");
    }

}
