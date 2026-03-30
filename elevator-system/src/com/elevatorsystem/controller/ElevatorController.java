package com.elevatorsystem.controller;

import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.ExternalRequest;
import com.elevatorsystem.strategy.ElevatorAssignmentStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller/Dispatcher – Mediates between external floor panels and elevators.
 */
public class ElevatorController {

    private final List<Elevator> elevators;
    private final ElevatorAssignmentStrategy assignmentStrategy;

    public ElevatorController(List<Elevator> elevators, ElevatorAssignmentStrategy assignmentStrategy) {
        this.elevators = elevators;
        this.assignmentStrategy = assignmentStrategy;
    }

    public synchronized void addExternalRequest(ExternalRequest request) {
        Elevator bestElevator = assignmentStrategy.findBestElevator(elevators, request);
        
        if (bestElevator != null) {
            System.out.println("[Controller] Assigning floor " + request.getFloor() + " " + request.getRequestedDirection() + " request to Elevator " + bestElevator.getId());
            bestElevator.addExternalRequest(request.getFloor(), request.getRequestedDirection());
        } else {
            System.out.println("[Controller] No elevator available for floor " + request.getFloor());
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}