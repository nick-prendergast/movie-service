package com.example.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class RatingDto {

    @NotEmpty
    private String title;

    @Min(value = 0, message = "number between 0 - 100 required")
    @Max(value = 100, message = "number between 0 - 100 required")
    private int movieRating;

}
