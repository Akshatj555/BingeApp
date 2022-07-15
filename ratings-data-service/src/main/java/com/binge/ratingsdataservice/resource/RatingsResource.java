package com.binge.ratingsdataservice.resource;

import com.binge.ratingsdataservice.model.Rating;
import com.binge.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){

        List<Rating> ratings = List.of(
                new Rating("100",4),
                new Rating("200",3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(ratings);
        return userRating;
    }

}
