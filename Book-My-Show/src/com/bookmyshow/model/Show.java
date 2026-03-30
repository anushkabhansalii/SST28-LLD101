package com.bookmyshow.model;

import com.bookmyshow.strategy.PricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a scheduled Show for a Movie at a Theatre.
 * Design Pattern: Strategy — pricing algorithm is pluggable at runtime.
 */
public class Show {

    private final String showId;
    private final Movie movie;
    private final Theatre theatre;
    private final LocalDateTime startTime;
    private final double basePrice;
    private final List<Seat> seats;
    private PricingStrategy pricingStrategy;

    public Show(String showId, Movie movie, Theatre theatre,
                LocalDateTime startTime, double basePrice,
                PricingStrategy pricingStrategy) {
        this.showId = showId;
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = startTime;
        this.basePrice = basePrice;
        this.pricingStrategy = pricingStrategy;
        this.seats = new ArrayList<>();
    }

    public String getShowId()                   { return showId; }
    public Movie getMovie()                     { return movie; }
    public Theatre getTheatre()                 { return theatre; }
    public LocalDateTime getStartTime()         { return startTime; }
    public double getBasePrice()                { return basePrice; }
    public List<Seat> getSeats()                { return seats; }
    public PricingStrategy getPricingStrategy() { return pricingStrategy; }

    /** Admin can update the pricing strategy at any time (OCP). */
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void addSeat(Seat seat) { seats.add(seat); }

    /** Returns only seats that are neither locked nor already booked. */
    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isLocked() && !seat.isBooked()) {
                available.add(seat);
            }
        }
        return available;
    }

    @Override
    public String toString() {
        return "Show{id='" + showId + "', movie='" + movie.getTitle()
                + "', theatre='" + theatre.getName()
                + "', time=" + startTime + '}';
    }
}