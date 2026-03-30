package com.bookmyshow.model;

import com.bookmyshow.enums.SeatType;

/**
 * Platinum-tier seat — the highest-priced premium seat.
 * Price multiplier: 2.0× the show's base price.
 */
public class PlatinumSeat extends Seat {

    private static final double PLATINUM_MULTIPLIER = 2.0;

    public PlatinumSeat(String seatId, String seatNumber) {
        super(seatId, seatNumber, SeatType.PLATINUM);
    }

    @Override
    public double getPriceMultiplier() {
        return PLATINUM_MULTIPLIER;
    }

    @Override
    public String toString() {
        return "PlatinumSeat{" + super.toString() + '}';
    }
}