package com.bookmyshow.strategy;

import com.bookmyshow.model.Seat;
import com.bookmyshow.model.Show;

/**
 * Strategy interface for pricing a seat in a show.
 */
public interface PricingStrategy {

    /**
     * Calculates the final price for a given seat in a given show.
     *
     * @param show 
     * @param seat 
     * @return 
     */
    double calculatePrice(Show show, Seat seat);
}