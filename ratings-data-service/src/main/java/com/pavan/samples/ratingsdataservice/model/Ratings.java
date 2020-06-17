package com.pavan.samples.ratingsdataservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name = "ratings")
@Entity
public class Ratings {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie_name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private UserRating user;

    public UserRating getUser() {
        return user;
    }

    public void setUser(UserRating user) {
        this.user = user;
    }

    public Ratings() {

    }

    public Ratings(Integer id, String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
