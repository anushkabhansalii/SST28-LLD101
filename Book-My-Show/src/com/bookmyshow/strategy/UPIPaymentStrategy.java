package com.bookmyshow.strategy;

import com.bookmyshow.enums.PaymentMode;
import com.bookmyshow.model.Booking;

/**
 * Payment via UPI (Unified Payments Interface).
 */
public class UPIPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount, Booking booking) {
        System.out.println("[UPIPayment] Processing ₹" + amount
                + " via UPI for booking " + booking.getBookingId());
        return true;
    }

    @Override
    public PaymentMode getPaymentMode() {
        return PaymentMode.UPI;
    }
}