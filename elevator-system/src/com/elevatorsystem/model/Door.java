package com.elevatorsystem.model;

import com.elevatorsystem.enums.DoorStatus;

/**
 * Door: only manages door state.
 * Supports optional obstruction detection
 */
public class Door {

    private DoorStatus status;
    private boolean obstructionDetected;

    public Door() {
        this.status = DoorStatus.CLOSED;
        this.obstructionDetected = false;
    }

    public void open() {
        this.status = DoorStatus.OPEN;
        System.out.println("[Door] Opened.");
    }

    public void close() {
        if (obstructionDetected) {
            System.out.println("[Door] Cannot close – obstruction detected!");
            return;
        }
        this.status = DoorStatus.CLOSED;
        System.out.println("[Door] Closed.");
    }

    public void setObstructionDetected(boolean obstructionDetected) {
        this.obstructionDetected = obstructionDetected;
    }

    public DoorStatus getStatus() {
        return status;
    }

    public boolean isObstructionDetected() {
        return obstructionDetected;
    }
}