package com.example.movieservice.configuration;

import com.example.movieservice.model.OmdbMovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "movie-service-api", url = "${movie-service.omdb-url}")
public interface ApiClient {

    @GetMapping(value = "/?t={movieTitle}&apikey={apiKey}")
    OmdbMovieDto getOmdbMovieDto(@PathVariable("movieTitle") String movieTitle, @PathVariable("apiKey") String apiKey);

}
