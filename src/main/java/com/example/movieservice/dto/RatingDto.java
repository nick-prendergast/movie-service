package com.example.movieservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonNaming(PropertyNamingStrategy..class)
public class RatingDto {

    @NotEmpty
    @JsonProperty("title")
    private String title;

    @Min(value = 0, message = "number between 0 - 100 required")
    @Max(value = 100, message = "number between 0 - 100 required")
    @JsonProperty("movieRating")
    private int movieRating;

}
