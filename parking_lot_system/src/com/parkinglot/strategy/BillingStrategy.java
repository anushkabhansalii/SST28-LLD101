package com.parkinglot.strategy;

import com.parkinglot.model.Bill;
import com.parkinglot.model.ParkingTicket;
import java.time.LocalDateTime;

/**
 * Strategy interface for billing.
 */
public interface BillingStrategy {
    /**
     * Generates a Bill for the given ticket at exitTime.
     *
     * @param ticket   
     * @param exitTime 
     * @return 
     */
    Bill generateBill(ParkingTicket ticket, LocalDateTime exitTime);
}