package com.bookmyshow.service;

import com.bookmyshow.model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SearchService — handles all read-only queries.
 */
public class SearchService {

    public List<Movie> listMoviesInCity(City city) {
        return city.getMovies();
    }

    public List<Theatre> listTheatresInCity(City city) {
        return city.getTheatres();
    }

    public List<Show> getShowsForMovieInCity(Movie movie, City city) {
        return city.getTheatres().stream()
                .flatMap(theatre -> theatre.getShows().stream())
                .filter(show -> show.getMovie().getMovieId().equals(movie.getMovieId()))
                .collect(Collectors.toList());
    }

    public List<Show> getShowsForTheatre(Theatre theatre) {
        return theatre.getShows();
    }

    public List<Seat> getAvailableSeatsForShow(Show show) {
        return show.getAvailableSeats();
    }
}