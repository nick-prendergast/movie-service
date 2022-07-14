package com.example.movieservice;

import com.example.movieservice.dto.RatingDto;
import com.example.movieservice.model.Rating;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MovieControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @Value("${movie-service.api-key}")
    private String apiKey;

    @Test
    void getMovie_MovieWinsBestPicture_Test() throws Exception {
        mvc.perform(get
                        ("/title/{movieName}/apikey={apiKey}", "The Hurt Locker", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("The Hurt Locker did win best picture"));

    }

    @Test
    void getMovie_MovieDoesNotWinBestPicture_Test() throws Exception {
        mvc.perform(get
                        ("/title/{movieName}/apikey={apiKey}", "Toy Story", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("did not win best picture"));

    }

    @Test
    void getMovie_invalidMovie_Test() throws Exception {
        mvc.perform(get
                        ("/title/{movieName}/apikey={apiKey}", "The Hurt Locker111", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()).andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("not valid due to :Invalid movie: does not exist in OMDB"));

    }

    @Test
    void rateMovie_Test() throws Exception {

        String ratingDTOJson = ratingJson("The Hurt Locker", 85);

        mvc.perform(post
                        ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("title").value("The Hurt Locker"))
                .andExpect(MockMvcResultMatchers.jsonPath("boxOffice").value("17017811"))
                .andExpect(MockMvcResultMatchers.jsonPath("movieRating").value(85));

    }

    @Test
    void rateMovie_invalidMovie_Test() throws Exception {

        String ratingDTOJson = ratingJson("The Hurt Locker111", 85);

        mvc.perform(post
                        ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson))
                .andExpect(status().isInternalServerError()).andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("not valid due to :Invalid movie: does not exist in OMDB"));

    }

    @Test
    void rateMovie_invalidRating_Test() throws Exception {

        String ratingDTOJson = ratingJson("The Hurt Locker111", 101);

        mvc.perform(post
                        ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson))
                .andExpect(status().isBadRequest()).andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("not valid due to validation error: movieRating: number between 0 - 100 required"));

    }

    @Test
    void getTopTenMovies_Test() throws Exception {
        String ratingDTOJson1 = ratingJson("The Hurt Locker", 85);
        String ratingDTOJson2 = ratingJson("Toy Story", 75);
        String ratingDTOJson3 = ratingJson("The Boy", 50);
        String ratingDTOJson4 = ratingJson("Adulthood", 55);
        String ratingDTOJson5 = ratingJson("The Matrix", 90);
        String ratingDTOJson6 = ratingJson("Fight Club", 100);
        String ratingDTOJson7 = ratingJson("American Psycho", 100);
        String ratingDTOJson8 = ratingJson("Ice Age", 20);
        String ratingDTOJson9 = ratingJson("Ice Age 2", 30);
        String ratingDTOJson10 = ratingJson("Toy Story 2", 40);
        String ratingDTOJson11 = ratingJson("Toy Story 3", 50);


        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson1)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson2)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson3)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson4)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson5)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson6)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson7)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson8)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson9)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson10)).andExpect(status().isOk());
        mvc.perform(post
                ("/rating/apikey={apiKey}", apiKey).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ratingDTOJson11)).andExpect(status().isOk());
        MvcResult test = mvc.perform(get
                        ("/rating/10").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andReturn();

        List<Rating> topTenList = Arrays.asList(new ObjectMapper().readValue(test.getResponse().getContentAsString(), Rating[].class));

        //check only 10 returned results
        Assertions.assertEquals(10, topTenList.size());

        //check movies are in desc order by box office value
        Assertions.assertTrue(IntStream.range(0, topTenList.size() - 1)
                .allMatch(i -> topTenList.get(i).getBoxOffice().compareTo(topTenList.get(i + 1).getBoxOffice()) >= 0));
    }

    private String ratingJson(String The_Hurt_Locker, int movieRating) throws JsonProcessingException {
        RatingDto ratingDto = new RatingDto(The_Hurt_Locker, movieRating);
        return objectMapper.writeValueAsString(ratingDto);
    }


}