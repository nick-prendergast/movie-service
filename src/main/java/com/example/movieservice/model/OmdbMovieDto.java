package com.example.movieservice.model;

import com.example.movieservice.validation.MovieValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovieDto {

    @MovieValidation
    private String title;

    private String awards;

    private String boxOffice;

    private String response;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int rating;

}
