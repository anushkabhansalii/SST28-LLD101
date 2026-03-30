package com.bookmyshow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a city which aggregates theatres and movies.
 */
public class City {

    private final String cityId;
    private final String name;
    private final List<Theatre> theatres;
    private final List<Movie> movies;

    public City(String cityId, String name) {
        this.cityId = cityId;
        this.name = name;
        this.theatres = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    public String getCityId()           { return cityId; }
    public String getName()             { return name; }
    public List<Theatre> getTheatres()  { return theatres; }
    public List<Movie> getMovies()      { return movies; }

    public void addTheatre(Theatre theatre) { theatres.add(theatre); }
    public void addMovie(Movie movie)       { movies.add(movie); }
}