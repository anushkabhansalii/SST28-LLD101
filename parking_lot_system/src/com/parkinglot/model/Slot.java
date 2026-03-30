package com.parkinglot.model;

import com.parkinglot.enums.SlotType;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Slot class representing a parking space.
 */
public abstract class Slot {
    private final String slotId;
    private final SlotType slotType;
    private final double pricePerHour;
    // Key = gateId, Value = distance from that gate (lower is closer)
    private final Map<String, Integer> distanceFromGates;
    private boolean isAvailable;
    private Vehicle parkedVehicle;

    protected Slot(String slotId, SlotType slotType, double pricePerHour) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.pricePerHour = pricePerHour;
        this.distanceFromGates = new HashMap<>();
        this.isAvailable = true;
        this.parkedVehicle = null;
    }

    /** Called during system initialization to record distances from each gate. */
    public void setDistanceFromGate(String gateId, int distance) {
        distanceFromGates.put(gateId, distance);
    }

    public int getDistanceFromGate(String gateId) {
        return distanceFromGates.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    /** Assigns a vehicle to this slot; marks it unavailable. */
    public void assignVehicle(Vehicle vehicle) {
        if (!isAvailable) {
            throw new IllegalStateException("Slot " + slotId + " is already occupied.");
        }
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
    }

    /** Removes the vehicle from this slot; marks it available again. */
    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isAvailable = true;
    }

    public String getSlotId() { return slotId; }
    public SlotType getSlotType() { return slotType; }
    public double getPricePerHour() { return pricePerHour; }
    public boolean isAvailable() { return isAvailable; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }

    @Override
    public String toString() {
        return slotType + "-Slot[" + slotId + "] available=" + isAvailable;
    }
}