package com.bookmyshow.model;

import com.bookmyshow.enums.SeatType;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Abstract base class for a seat in a Show.
 */
public abstract class Seat {

    private final String seatId;
    private final String seatNumber;
    private final SeatType seatType;

    /** Per-seat lock for concurrency safety. */
    private final ReentrantLock lock;

    private volatile boolean isLocked;   // true while a booking transaction holds it
    private volatile boolean isBooked;   // true after payment is confirmed

    protected Seat(String seatId, String seatNumber, SeatType seatType) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.lock = new ReentrantLock(true); // fair lock
        this.isLocked = false;
        this.isBooked = false;
    }

    
    public abstract double getPriceMultiplier();

    public boolean tryLock() {
        if (lock.tryLock()) {
            if (!isLocked && !isBooked) {
                isLocked = true;
                return true;
            }
            lock.unlock(); // seat already taken — release immediately
        }
        return false;
    }

    public void releaseLock() {
        if (lock.isHeldByCurrentThread()) {
            isLocked = false;
            lock.unlock();
        }
    }

    public void book() {
        isBooked = true;
        releaseLock();
    }

    public void release() {
        isBooked = false;
        isLocked = false;
    }

    public String getSeatId()       { return seatId; }
    public String getSeatNumber()   { return seatNumber; }
    public SeatType getSeatType()   { return seatType; }
    public boolean isLocked()       { return isLocked; }
    public boolean isBooked()       { return isBooked; }

    @Override
    public String toString() {
        return "Seat{id='" + seatId + "', num='" + seatNumber
                + "', type=" + seatType
                + ", locked=" + isLocked + ", booked=" + isBooked + '}';
    }
}