package com.elevatorsystem;

import com.elevatorsystem.model.Building;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.Floor;

/**
 * Main application to demonstrate the Elevator System LLD.
 */
public class Main {
    public static void main(String[] args) {
        // Init building with 10 floors and 2 elevators
        Building building = Building.getInstance(10, 2);
        
        System.out.println("--- Starting Elevator System Simulation ---");

        // External request: Someone on floor 5 wants to go UP
        Floor floor5 = building.getFloors().get(5);
        floor5.getExternalPanel().pressUp();

        // External request: Someone on floor 2 wants to go DOWN
        Floor floor2 = building.getFloors().get(2);
        floor2.getExternalPanel().pressDown();

        // Process elevators (in a real system, this happens continually)
        for (Elevator elevator : building.getElevators()) {
            elevator.move();
        }

        // Internal request: Person in LIFT-1 wants to go to floor 8
        Elevator lift1 = building.getElevators().get(0);
        lift1.getInternalPanel().selectFloor(8);
        
        // Move lift-1 again
        lift1.move();
        lift1.move();
        
        System.out.println("--- End Simulation ---");
    }
}