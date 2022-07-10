package com.example.movieservice.configuration;

import com.example.movieservice.model.OmdbMovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "movie-service-api", url = "${omdb-url}")
public interface ApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/?t={movieTitle}&apikey={apiKey}")
    OmdbMovieDto getOmdbMovieDto(@PathVariable("movieTitle") String movieTitle, @PathVariable("apiKey") String apiKey);

}
