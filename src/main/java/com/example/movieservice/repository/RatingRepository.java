package com.example.movieservice.repository;

import com.example.movieservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "SELECT r.id, r.title, r.box_office, r.rating from Rating r ORDER BY r.box_office DESC LIMIT 10", nativeQuery = true)
    List<Rating> findTop10ByRatingOrderByBoxOffice();

}
