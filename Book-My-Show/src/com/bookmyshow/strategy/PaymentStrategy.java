package com.bookmyshow.strategy;

import com.bookmyshow.model.Booking;
import com.bookmyshow.enums.PaymentMode;

/**
 * Strategy interface for all payment processing methods.
 */
public interface PaymentStrategy {

    /**
     * Processes the payment for the given booking.
     *
     * @param amount  
     * @param booking 
     * @return
     */
    boolean pay(double amount, Booking booking);

    PaymentMode getPaymentMode();
}