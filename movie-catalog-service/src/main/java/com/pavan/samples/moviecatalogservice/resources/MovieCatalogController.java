package com.pavan.samples.moviecatalogservice.resources;

import com.pavan.samples.moviecatalogservice.model.CatalogItem;
import com.pavan.samples.moviecatalogservice.model.Movie;
import com.pavan.samples.moviecatalogservice.model.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    Logger log = LoggerFactory.getLogger(MovieCatalogController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${ratings.data.service.url}")
    private String ratingsUrl;

    @Value("${info.service.url}")
    private String infoUrl;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") Integer userId) {
        log.info(ratingsUrl + userId);
        log.info(infoUrl);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        UserRating ratings = restTemplate.getForObject(ratingsUrl + userId, UserRating.class);
        log.info(ratings.getUserRating().get(0).toString());
        log.info(ratings.toString());
        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject(infoUrl + rating.getMovieId(), Movie.class);

            return new CatalogItem(movie.getName(), "Dreams", rating.getRating());
        }).collect(Collectors.toList());
    }
}

 /* Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/
