package com.elevatorsystem.strategy;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.ExternalRequest;
import java.util.List;

/**
 * Concrete strategy: Assigns the nearest idle elevator or one moving in the same direction.
 */
public class NearestIdleStrategy implements ElevatorAssignmentStrategy {

    @Override
    public Elevator findBestElevator(List<Elevator> elevators, ExternalRequest request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.isOverloaded()) continue;

            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
            
            // Check if elevator is moving in the requested direction or is IDLE
            boolean canPickUp = false;
            if (elevator.getCurrentDirection() == Direction.IDLE) {
                canPickUp = true;
            } else if (elevator.getCurrentDirection() == request.getRequestedDirection()) {
                // Moving in same direction. Only better if it hasn't passed the floor.
                if (elevator.getCurrentDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getFloor()) {
                    canPickUp = true;
                } else if (elevator.getCurrentDirection() == Direction.DOWN && elevator.getCurrentFloor() >= request.getFloor()) {
                    canPickUp = true;
                }
            }

            if (canPickUp && distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        // If no optimal elevator found, pick any available elevator as fallback
        if (bestElevator == null && !elevators.isEmpty()) {
            bestElevator = elevators.get(0);
        }

        return bestElevator;
    }
}