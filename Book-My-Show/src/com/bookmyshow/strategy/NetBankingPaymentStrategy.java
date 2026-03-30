package com.bookmyshow.strategy;

import com.bookmyshow.enums.PaymentMode;
import com.bookmyshow.model.Booking;

/**
 * Payment via Net Banking.
 */
public class NetBankingPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount, Booking booking) {
        System.out.println("[NetBanking] Initiating bank transfer of ₹" + amount
                + " for booking " + booking.getBookingId());
        return true;
    }

    @Override
    public PaymentMode getPaymentMode() {
        return PaymentMode.NET_BANKING;
    }
}