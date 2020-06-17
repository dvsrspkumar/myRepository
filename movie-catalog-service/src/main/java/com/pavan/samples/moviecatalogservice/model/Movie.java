package com.pavan.samples.moviecatalogservice.model;

public class Movie {

    private String name;
    private int id;

    public Movie() {
    }

    public Movie(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
