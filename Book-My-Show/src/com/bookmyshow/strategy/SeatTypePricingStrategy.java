package com.bookmyshow.strategy;

import com.bookmyshow.model.Seat;
import com.bookmyshow.model.Show;

/**
 * Concrete pricing strategy that calculates final seat price as:
 *      finalPrice = show.basePrice × seat.priceMultiplier
 *
 * Gold seats     → 1.5× base price
 * Platinum seats → 2.0× base price
 */
public class SeatTypePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Show show, Seat seat) {
        return show.getBasePrice() * seat.getPriceMultiplier();
    }
}