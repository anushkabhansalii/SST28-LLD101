package com.elevatorsystem.strategy;

import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.ExternalRequest;
import java.util.List;

/**
 * Strategy pattern interface for elevator assignment.
 */
public interface ElevatorAssignmentStrategy {
    Elevator findBestElevator(List<Elevator> elevators, ExternalRequest request);
}