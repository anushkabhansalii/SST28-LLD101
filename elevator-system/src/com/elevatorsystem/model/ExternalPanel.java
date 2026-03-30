package com.elevatorsystem.model;

import com.elevatorsystem.controller.ElevatorController;
import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.model.ExternalRequest;

/**
 * External (Hall) Panel – mounted on each floor.
 * Users can press UP or DOWN.
 */
public class ExternalPanel {

    private final int floorNumber;
    private ElevatorController controller;

    public ExternalPanel(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setController(ElevatorController controller) {
        this.controller = controller;
    }

    public void pressUp() {
        System.out.println("[Hall Panel Floor-" + floorNumber + "] UP pressed.");
        if (controller != null) {
            controller.addExternalRequest(new ExternalRequest(floorNumber, Direction.UP));
        }
    }

    public void pressDown() {
        System.out.println("[Hall Panel Floor-" + floorNumber + "] DOWN pressed.");
        if (controller != null) {
            controller.addExternalRequest(new ExternalRequest(floorNumber, Direction.DOWN));
        }
    }
}