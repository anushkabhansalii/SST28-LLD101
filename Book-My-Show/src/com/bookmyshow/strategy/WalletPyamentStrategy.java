package com.bookmyshow.strategy;

import com.bookmyshow.enums.PaymentMode;
import com.bookmyshow.model.Booking;

/**
 * Payment via digital wallets (Paytm, PhonePe, Amazon Pay, etc.).
 */
public class WalletPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount, Booking booking) {
        System.out.println("[WalletPayment] Debiting ₹" + amount
                + " from wallet for booking " + booking.getBookingId());
        return true;
    }

    @Override
    public PaymentMode getPaymentMode() {
        return PaymentMode.WALLET;
    }
}