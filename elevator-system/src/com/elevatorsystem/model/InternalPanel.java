package com.elevatorsystem.model;

import com.elevatorsystem.model.InternalRequest;

/**
 * Internal (In-Cabin) Panel – each elevator has one.
 * Users select destination floors.
 * Delegates directly to the associated Elevator.
 */
public class InternalPanel {

    private final Elevator elevator;

    public InternalPanel(Elevator elevator) {
        this.elevator = elevator;
    }

    public void selectFloor(int floor) {
        System.out.println("[Inner Panel Elevator-" + elevator.getId() + "] Floor " + floor + " selected.");
        elevator.addInternalRequest(new InternalRequest(floor));
    }
}