package com.example.movieservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("movie-service")
@Data
public class ConfigProperties {

    private String apiKey;

    private String omdbUrl;

    private String csvFile;

}
