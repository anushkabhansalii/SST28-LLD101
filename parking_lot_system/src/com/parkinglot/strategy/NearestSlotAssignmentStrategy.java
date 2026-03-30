package com.parkinglot.strategy;

import com.parkinglot.enums.SlotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Floor;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Slot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Assigns the available slot NEAREST to the given entry gate.
 * Strategy Pattern: concrete implementation of SlotAssignmentStrategy.
 */
public class NearestSlotAssignmentStrategy implements SlotAssignmentStrategy {

    @Override
    public Slot assignSlot(VehicleType vehicleType, String gateId, ParkingLot parkingLot) {
        SlotType required = mapVehicleToSlotType(vehicleType);

        // Gather all available slots matching the required type across all floors
        List<Slot> candidates = parkingLot.getFloors().stream()
                .flatMap(floor -> floor.getSlots().stream())
                .filter(s -> s.getSlotType() == required && s.isAvailable())
                .sorted(Comparator.comparingInt(s -> s.getDistanceFromGate(gateId)))
                .collect(Collectors.toList());

        if (candidates.isEmpty()) {
            return null;
        }

        Slot nearest = candidates.get(0);
        return nearest;  
    }

    private SlotType mapVehicleToSlotType(VehicleType vehicleType) {
        switch (vehicleType) {
            case TWO_WHEELER:   return SlotType.SMALL;
            case CAR:           return SlotType.MEDIUM;
            case HEAVY_VEHICLE: return SlotType.LARGE;
            default: throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}