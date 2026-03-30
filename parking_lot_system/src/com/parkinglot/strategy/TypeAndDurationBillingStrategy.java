package com.parkinglot.strategy;

import com.parkinglot.model.Bill;
import com.parkinglot.model.ParkingTicket;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Bills based on slot type (price per hour) × duration parked.
 */
public class TypeAndDurationBillingStrategy implements BillingStrategy {

    @Override
    public Bill generateBill(ParkingTicket ticket, LocalDateTime exitTime) {
        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long hours = (minutes <= 0) ? 1 : (long) Math.ceil(minutes / 60.0);
        if (hours == 0) hours = 1;  

        double rate = ticket.getAssignedSlot().getPricePerHour();
        double totalAmount = rate * hours;

        return new Bill(ticket, exitTime, totalAmount);
    }
}