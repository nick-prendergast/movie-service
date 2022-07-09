package com.example.movieservice.service;

import com.example.movieservice.model.OmdbMovieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
//@RequiredArgsConstructor
public class OmdbService {

//    @Value("${api-key}")
    private final String apiKey;

    private final String omdbUrl;

    public OmdbService(@Value("${api-key}") String apiKey, @Value("${omdb-url}") String omdbUrl) {
        this.apiKey = apiKey;
        this.omdbUrl = omdbUrl;
    }

    public OmdbMovieDto getOmdbMovie(String movieName) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE));
        messageConverters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host(omdbUrl).path("/").query("t={movieTitle}&apikey={apikey}").buildAndExpand(movieName, apiKey).encode();
        String uriString = uriComponents.toUriString();
        log.info("Checking OMDB API for {} at {}", movieName, uriString);
        return restTemplate.getForObject(uriComponents.toUri(), OmdbMovieDto.class);
    }
}
