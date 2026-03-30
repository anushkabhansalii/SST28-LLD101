package com.elevatorsystem.model;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.RequestType;

/**
 * Represents an external (hall-panel) request.
 * A user presses the UP or DOWN button on a floor.
 */
public class ExternalRequest extends Request {

    private final Direction requestedDirection;

    public ExternalRequest(int floor, Direction requestedDirection) {
        super(floor);
        this.requestedDirection = requestedDirection;
    }

    public Direction getRequestedDirection() {
        return requestedDirection;
    }

    public RequestType getType() {
        return RequestType.EXTERNAL;
    }

    @Override
    public String toString() {
        return "ExternalRequest{floor=" + floor + ", direction=" + requestedDirection + ", served=" + isServed + "}";
    }
}