package com.example.movieservice.repository;

import com.example.movieservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = "SELECT * FROM (SELECT r.id, r.title, r.box_office, r.movie_rating from Rating r ORDER BY r.movie_rating DESC LIMIT :findTopNMovies) AS T1 ORDER BY T1.box_office DESC", nativeQuery = true)
    List<Rating> findTopMoviesByRatingOrderByBoxOffice(@Param("findTopNMovies") int findTopNMovies);

}
