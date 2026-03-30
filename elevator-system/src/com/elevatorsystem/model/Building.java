package com.elevatorsystem.model;

import com.elevatorsystem.controller.ElevatorController;
import com.elevatorsystem.strategy.NearestIdleStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * Building Singleton – Represents the entire system.
 * Ties floors, elevators, and controller together.
 */
public class Building {

    private static Building instance;
    private final List<Floor> floors;
    private final List<Elevator> elevators;
    private final ElevatorController controller;

    private Building(int totalFloors, int totalElevators) {
        this.floors = new ArrayList<>();
        this.elevators = new ArrayList<>();
        
        // Initialize elevators
        for (int i = 1; i <= totalElevators; i++) {
            elevators.add(new Elevator("LIFT-" + i, 10)); // Default capacity 10
        }

        // Setup controller
        this.controller = new ElevatorController(elevators, new NearestIdleStrategy());

        // Initialize floors and inject controller contact to hall panels
        for (int i = 0; i < totalFloors; i++) {
            Floor floor = new Floor(i);
            floor.getExternalPanel().setController(controller);
            floors.add(floor);
        }
    }

    public static synchronized Building getInstance(int floors, int lifts) {
        if (instance == null) {
            instance = new Building(floors, lifts);
        }
        return instance;
    }

    public List<Floor> getFloors() { return floors; }
    public List<Elevator> getElevators() { return elevators; }
    public ElevatorController getController() { return controller; }
}