package com.parkinglot.model;

import com.parkinglot.strategy.SlotAssignmentStrategy;

/**
 * Represents an entry gate.
 */
public class EntryGate {
    private final String gateId;
    private final SlotAssignmentStrategy assignmentStrategy;

    public EntryGate(String gateId, SlotAssignmentStrategy assignmentStrategy) {
        this.gateId = gateId;
        this.assignmentStrategy = assignmentStrategy;
    }

    /**
     * Generates a ParkingTicket for the given vehicle.
     * Finds nearest available slot via strategy, assigns the vehicle, returns the ticket.
     *
     * @param vehicle    
     * @param parkingLot 
     * @return 
     */
    public ParkingTicket generateTicket(Vehicle vehicle, ParkingLot parkingLot) {
        Slot slot = assignmentStrategy.assignSlot(vehicle.getVehicleType(), gateId, parkingLot);
        if (slot == null) {
            System.out.println("[" + gateId + "] No available slot for " + vehicle.getVehicleType());
            return null;
        }
        slot.assignVehicle(vehicle);
        ParkingTicket ticket = new ParkingTicket(vehicle, slot, gateId);
        System.out.println("[" + gateId + "] Ticket issued:\n" + ticket);
        return ticket;
    }

    public String getGateId() { return gateId; }
}