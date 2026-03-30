package com.parkinglot.display;

import com.parkinglot.enums.SlotType;
import com.parkinglot.model.Floor;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Slot;

/**
 * DisplayBoard shows real-time availability across the parking lot.
 * SRP: Solely responsible for display / status reporting.
 */
public class DisplayBoard {

    /**
     * Prints a formatted availability status for all floors and slot types.
     */
    public void showStatus(ParkingLot parkingLot) {
        System.out.println("\n========== Parking Lot Status ==========");
        System.out.printf("%-10s %-10s %-10s %-10s%n", "Floor", "SMALL", "MEDIUM", "LARGE");
        System.out.println("----------------------------------------");

        for (Floor floor : parkingLot.getFloors()) {
            long small  = floor.countAvailable(SlotType.SMALL);
            long medium = floor.countAvailable(SlotType.MEDIUM);
            long large  = floor.countAvailable(SlotType.LARGE);
            System.out.printf("Floor %-5d %-10d %-10d %-10d%n",
                    floor.getFloorNumber(), small, medium, large);
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-10s %-10d %-10d %-10d%n",
                "TOTAL",
                parkingLot.getAvailableSlots(SlotType.SMALL),
                parkingLot.getAvailableSlots(SlotType.MEDIUM),
                parkingLot.getAvailableSlots(SlotType.LARGE));
        System.out.println("========================================\n");
    }
}