package com.pavan.samples.ratingsdataservice.resources.repository;

import com.pavan.samples.ratingsdataservice.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRating, Integer> {
}
