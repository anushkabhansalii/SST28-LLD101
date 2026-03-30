package com.bookmyshow.model;

import com.bookmyshow.enums.SeatType;

/**
 * Gold-tier seat — moderately priced premium seat.
 * Price multiplier: 1.5× the show's base price.
 */
public class GoldSeat extends Seat {

    private static final double GOLD_MULTIPLIER = 1.5;

    public GoldSeat(String seatId, String seatNumber) {
        super(seatId, seatNumber, SeatType.GOLD);
    }

    @Override
    public double getPriceMultiplier() {
        return GOLD_MULTIPLIER;
    }

    @Override
    public String toString() {
        return "GoldSeat{" + super.toString() + '}';
    }
}