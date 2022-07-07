package com.binge.moviecatalogservice.resource;

import com.binge.moviecatalogservice.model.CatalogItem;
import com.binge.moviecatalogservice.model.Movie;
import com.binge.moviecatalogservice.model.Rating;
import com.binge.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

     @Autowired
     private RestTemplate restTemplate;


     @RequestMapping("/{userId}")
     List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

//          List<Rating> ratings =
          UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

          return userRating.getUserRating().stream().map(rating -> {
               Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
               return new CatalogItem(movie.getName(),"Test", rating.getRating());
               }).collect(Collectors.toList());

     }
}
