package com.pavan.samples.ratingsdataservice.resources.services;

import com.pavan.samples.ratingsdataservice.model.Ratings;
import com.pavan.samples.ratingsdataservice.model.UserRating;
import com.pavan.samples.ratingsdataservice.resources.repository.RatingsRepository;
import com.pavan.samples.ratingsdataservice.resources.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class RatingsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RatingsRepository ratingRepo;

    public UserRating getUserRatings(Integer userId) {

        return userRepo.findById(userId).get();
    }

    public Ratings getRatings(Integer movieId) {
        return ratingRepo.findById(movieId).get();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public UserRating addUserRatings(UserRating user, List<Ratings> ratings) {

        UserRating userRating = new UserRating();
        if (user != null) {
            userRating.setId(user.getId());
            userRating.setName(user.getName());
            userRating.setAge(user.getAge());
            userRating.setGender(user.getGender());
            userRepo.save(userRating);

            ratings.forEach(rating -> {
                rating.setUser(userRating);
            });
            ratingRepo.saveAll(ratings);
            userRating.setUserRating(ratings);
        }
        return userRating;
    }
}
