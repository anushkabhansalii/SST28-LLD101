package com.elevatorsystem.model;

/**
 * Represents a request made to the elevator system.
 */
public abstract class Request {

    protected final int floor;          // Target or source floor
    protected boolean isServed;

    public Request(int floor) {
        this.floor = floor;
        this.isServed = false;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isServed() {
        return isServed;
    }

    public void markServed() {
        this.isServed = true;
    }

    @Override
    public String toString() {
        return "Request{floor=" + floor + ", served=" + isServed + "}";
    }
}