package com.example.movieservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Omdb {


    @JsonProperty("Title")
    private String title;

    @JsonProperty("Awards")
    private String awards;

    @JsonProperty("BoxOffice")
    private String boxOffice;

    @JsonProperty("Response")
    private String response;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int rating;

}
