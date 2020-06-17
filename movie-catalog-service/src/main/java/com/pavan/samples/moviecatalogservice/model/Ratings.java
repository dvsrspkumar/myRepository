package com.pavan.samples.moviecatalogservice.model;

public class Ratings {

    private Integer id;
    private String name;
    private int rating;

    public Ratings() {
    }

    public Ratings(Integer movieId, String name, int rating) {
        this.id = movieId;
        this.name = name;
        this.rating = rating;
    }

    public Integer getMovieId() {
        return id;
    }

    public void setMovieId(Integer movieId) {
        this.id = movieId;
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

    @Override
    public String toString() {
        return "Ratings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
