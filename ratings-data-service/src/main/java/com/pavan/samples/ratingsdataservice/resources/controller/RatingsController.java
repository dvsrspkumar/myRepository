package com.pavan.samples.ratingsdataservice.resources.controller;

import com.pavan.samples.ratingsdataservice.model.Ratings;
import com.pavan.samples.ratingsdataservice.model.UserRating;
import com.pavan.samples.ratingsdataservice.resources.exception.ResourceNotFoundException;
import com.pavan.samples.ratingsdataservice.resources.services.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    RatingsService service;

    @GetMapping("/{movieId}")
    public Ratings getRatings(@PathVariable("movieId") Integer movieId) throws ResourceNotFoundException {
        return service.getRatings(movieId);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        return service.getUserRatings(userId);
    }

    @PostMapping(path = "/add")
    public UserRating addUserRating(@RequestBody UserRating user,
                                    @RequestBody List<Ratings> ratings) throws ResourceNotFoundException {
        return service.addUserRatings(user, ratings);
    }
}
