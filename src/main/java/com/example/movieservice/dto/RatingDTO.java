package com.example.movieservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class RatingDTO {

    @NotEmpty
    private String title;

    @JsonProperty("rating")
    @Min(value = 0, message = "number between 0 - 100 required")
    @Max(value = 100, message = "number between 0 - 100 required")
    private int movieRating;

}
