package com.bookmyshow.strategy;

import com.bookmyshow.enums.PaymentMode;
import com.bookmyshow.model.Booking;

/**
 * Payment via Credit or Debit card.
 */
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount, Booking booking) {
        System.out.println("[CardPayment] Charging ₹" + amount
                + " for booking " + booking.getBookingId());
        // Simulate successful card transaction
        return true;
    }

    @Override
    public PaymentMode getPaymentMode() {
        return PaymentMode.CREDIT_CARD; 
    }
}