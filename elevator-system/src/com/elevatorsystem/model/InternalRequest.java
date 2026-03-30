package com.elevatorsystem.model;

import com.elevatorsystem.enums.RequestType;

/**
 * Represents an internal (in-cabin panel) request.
 * A user inside the elevator selects a destination floor.
 */
public class InternalRequest extends Request {

    public InternalRequest(int destinationFloor) {
        super(destinationFloor);
    }

    public RequestType getType() {
        return RequestType.INTERNAL;
    }

    @Override
    public String toString() {
        return "InternalRequest{destinationFloor=" + floor + ", served=" + isServed + "}";
    }
}