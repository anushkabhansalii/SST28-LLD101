package com.parkinglot.strategy;

import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Slot;
import com.parkinglot.enums.VehicleType;

/**
 * Strategy interface for slot assignment.
 */
public interface SlotAssignmentStrategy {
    /**
     * Finds and returns the best available slot for the given vehicle type
     * from the entry gate identified by gateId.
     *
     * @param vehicleType 
     * @param gateId      
     * @param parkingLot  
     * @return 
     */
    Slot assignSlot(VehicleType vehicleType, String gateId, ParkingLot parkingLot);
}