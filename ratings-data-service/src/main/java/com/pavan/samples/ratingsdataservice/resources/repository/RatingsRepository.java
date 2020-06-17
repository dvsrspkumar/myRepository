package com.pavan.samples.ratingsdataservice.resources.repository;

import com.pavan.samples.ratingsdataservice.model.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingsRepository extends JpaRepository<Ratings, Integer> {

    List<Ratings> findByUserId(Integer userId);

    Optional<Ratings> findByIdAndUserId(Integer id, Integer userId);
}
