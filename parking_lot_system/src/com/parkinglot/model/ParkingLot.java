package com.parkinglot.model;

import com.parkinglot.enums.SlotType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton representing the entire Parking Lot.
 * Singleton Pattern : only one instance exists across the JVM.
 */
public class ParkingLot {

    // Singleton 
    private static ParkingLot instance;

    private ParkingLot() {}

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    private List<Floor>      floors     = new ArrayList<>();
    private List<EntryGate>  entryGates = new ArrayList<>();
    private List<ExitGate>   exitGates  = new ArrayList<>();

    public void addFloor(Floor floor)         { floors.add(floor); }
    public void addEntryGate(EntryGate gate)  { entryGates.add(gate); }
    public void addExitGate(ExitGate gate)    { exitGates.add(gate); }

    public List<Floor>      getFloors()      { return Collections.unmodifiableList(floors); }
    public List<EntryGate>  getEntryGates()  { return Collections.unmodifiableList(entryGates); }
    public List<ExitGate>   getExitGates()   { return Collections.unmodifiableList(exitGates); }

    public int getAvailableSlots(SlotType type) {
        return (int) floors.stream()
                .flatMap(f -> f.getSlots().stream())
                .filter(s -> s.getSlotType() == type && s.isAvailable())
                .count();
    }

    /** Convenience: find EntryGate by ID. */
    public EntryGate getEntryGateById(String gateId) {
        return entryGates.stream()
                .filter(g -> g.getGateId().equals(gateId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No entry gate found: " + gateId));
    }
}