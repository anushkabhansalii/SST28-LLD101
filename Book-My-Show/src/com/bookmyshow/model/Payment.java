package com.bookmyshow.model;

import com.bookmyshow.enums.PaymentMode;
import com.bookmyshow.enums.PaymentStatus;

import java.time.LocalDateTime;

/**
 * Represents a payment transaction linked to a Booking.
 */
public class Payment {

    private final String paymentId;
    private final Booking booking;
    private final double amount;
    private final PaymentMode mode;
    private final LocalDateTime paymentTime;

    private PaymentStatus status;

    public Payment(String paymentId, Booking booking, double amount, PaymentMode mode) {
        this.paymentId = paymentId;
        this.booking = booking;
        this.amount = amount;
        this.mode = mode;
        this.paymentTime = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
    }

    public String getPaymentId()        { return paymentId; }
    public Booking getBooking()         { return booking; }
    public double getAmount()           { return amount; }
    public PaymentMode getMode()        { return mode; }
    public LocalDateTime getPaymentTime(){ return paymentTime; }
    public PaymentStatus getStatus()    { return status; }

    public void setStatus(PaymentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Payment{id='" + paymentId + "', amount=" + amount
                + ", mode=" + mode + ", status=" + status + '}';
    }
}