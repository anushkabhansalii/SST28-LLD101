package com.bookmyshow.service;

import com.bookmyshow.enums.BookingStatus;
import com.bookmyshow.enums.PaymentStatus;
import com.bookmyshow.model.*;
import com.bookmyshow.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BookingService
 *
 * Singleton Pattern:
 *  BookingService is a single shared instance so all booking requests
 *  coordinate through one lock manager — no two threads can book the same seat.
 *
 */
public class BookingService {

    private static volatile BookingService instance;

    private BookingService() {}

    public static BookingService getInstance() {
        if (instance == null) {
            synchronized (BookingService.class) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }


    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();


    /**
     * Creates an atomic booking for the requested seats in a show.
     *
     * @param show          
     * @param requestedSeats 
     * @param userId          
     * @param paymentStrategy 
     * @return 
     */
    public Booking createBooking(Show show, List<Seat> requestedSeats,
                                 String userId, PaymentStrategy paymentStrategy) {

        List<Seat> sortedSeats = new ArrayList<>(requestedSeats);
        sortedSeats.sort((a, b) -> a.getSeatId().compareTo(b.getSeatId()));

        List<Seat> lockedSeats = new ArrayList<>();

        for (Seat seat : sortedSeats) {
            if (!seat.tryLock()) {
                // Rollback: release all locks acquired so far
                for (Seat locked : lockedSeats) {
                    locked.releaseLock();
                }
                System.out.println("[BookingService] Seat " + seat.getSeatNumber()
                        + " is unavailable. Booking rolled back.");
                return null;
            }
            lockedSeats.add(seat);
        }

        double totalAmount = 0;
        for (Seat seat : sortedSeats) {
            totalAmount += show.getPricingStrategy().calculatePrice(show, seat);
        }

        String bookingId = "BKG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Booking booking = new Booking(bookingId, show, sortedSeats, userId, totalAmount);

        Payment payment = new Payment(
                "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                booking,
                totalAmount,
                paymentStrategy.getPaymentMode()
        );

        boolean paymentSuccess = paymentStrategy.pay(totalAmount, booking);

        if (!paymentSuccess) {
            // Rollback: release all seat locks on payment failure
            for (Seat seat : lockedSeats) {
                seat.releaseLock();
            }
            payment.setStatus(PaymentStatus.FAILED);
            booking.setStatus(BookingStatus.CANCELLED);
            System.out.println("[BookingService] Payment failed. Booking " + bookingId + " cancelled.");
            return null;
        }

        payment.setStatus(PaymentStatus.SUCCESS);
        booking.setPayment(payment);
        booking.setStatus(BookingStatus.CONFIRMED);

        for (Seat seat : lockedSeats) {
            seat.book(); // marks isBooked=true and releases the ReentrantLock
        }

        bookings.put(bookingId, booking);
        System.out.println("[BookingService] Booking confirmed: " + booking);
        return booking;
    }

    /**
     * Cancels a booking and releases all its seats back to available.
     * Also marks the payment as REFUNDED.
     *
     * @param bookingId ID of the booking to cancel
     * @return true if cancellation was successful
     */
    public boolean cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            System.out.println("[BookingService] Booking not found: " + bookingId);
            return false;
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("[BookingService] Booking already cancelled: " + bookingId);
            return false;
        }

        for (Seat seat : booking.getSeats()) {
            seat.release();
        }
        booking.setStatus(BookingStatus.CANCELLED);
        if (booking.getPayment() != null) {
            booking.getPayment().setStatus(PaymentStatus.REFUNDED);
        }

        System.out.println("[BookingService] Booking cancelled and seats released: " + bookingId);
        return true;
    }


    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }
}