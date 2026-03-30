package com.bookmyshow.service;

import com.bookmyshow.model.*;
import com.bookmyshow.strategy.PricingStrategy;

/**
 * AdminService — handles all administrative mutations.
 */
public class AdminService {

    public void addMovie(City city, Movie movie) {
        city.addMovie(movie);
        System.out.println("[AdminService] Added movie '" + movie.getTitle()
                + "' to city '" + city.getName() + "'");
    }

    public void addTheatre(City city, Theatre theatre) {
        city.addTheatre(theatre);
        System.out.println("[AdminService] Added theatre '" + theatre.getName()
                + "' to city '" + city.getName() + "'");
    }

    public void addShow(Theatre theatre, Show show) {
        theatre.addShow(show);
        System.out.println("[AdminService] Added show '" + show.getShowId()
                + "' to theatre '" + theatre.getName() + "'");
    }

    public void deleteShow(Theatre theatre, Show show) {
        theatre.removeShow(show);
        System.out.println("[AdminService] Deleted show '" + show.getShowId()
                + "' from theatre '" + theatre.getName() + "'");
    }

    public void addSeatToShow(Show show, Seat seat) {
        show.addSeat(seat);
        System.out.println("[AdminService] Added seat '" + seat.getSeatNumber()
                + "' (" + seat.getSeatType() + ") to show " + show.getShowId());
    }

    public void updatePricingStrategy(Show show, PricingStrategy strategy) {
        show.setPricingStrategy(strategy);
        System.out.println("[AdminService] Updated pricing strategy for show "
                + show.getShowId() + " to " + strategy.getClass().getSimpleName());
    }
}