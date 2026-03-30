package com.bookmyshow.model;

import com.bookmyshow.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a user's booking for a set of seats in a Show.
 */
public class Booking {

    private final String bookingId;
    private final Show show;
    private final List<Seat> seats;
    private final String userId;
    private final LocalDateTime bookingTime;
    private final double totalAmount;

    private BookingStatus status;
    private Payment payment;

    public Booking(String bookingId, Show show, List<Seat> seats,
                   String userId, double totalAmount) {
        this.bookingId = bookingId;
        this.show = show;
        this.seats = seats;
        this.userId = userId;
        this.bookingTime = LocalDateTime.now();
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

    public String getBookingId()        { return bookingId; }
    public Show getShow()               { return show; }
    public List<Seat> getSeats()        { return seats; }
    public String getUserId()           { return userId; }
    public LocalDateTime getBookingTime(){ return bookingTime; }
    public double getTotalAmount()      { return totalAmount; }
    public BookingStatus getStatus()    { return status; }
    public Payment getPayment()         { return payment; }

    public void setStatus(BookingStatus status) { this.status = status; }
    public void setPayment(Payment payment)     { this.payment = payment; }

    @Override
    public String toString() {
        return "Booking{id='" + bookingId + "', show='" + show.getShowId()
                + "', user='" + userId + "', seats=" + seats.size()
                + ", amount=" + totalAmount + ", status=" + status + '}';
    }
}