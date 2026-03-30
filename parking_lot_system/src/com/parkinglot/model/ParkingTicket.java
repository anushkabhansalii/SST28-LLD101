package com.parkinglot.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a parking ticket issued on vehicle entry.
 */
public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final Slot assignedSlot;
    private final LocalDateTime entryTime;
    private final String entryGateId;

    public ParkingTicket(Vehicle vehicle, Slot assignedSlot, String entryGateId) {
        this.ticketId = "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.vehicle = vehicle;
        this.assignedSlot = assignedSlot;
        this.entryTime = LocalDateTime.now();
        this.entryGateId = entryGateId;
    }

    public String getTicketId()         { return ticketId; }
    public Vehicle getVehicle()         { return vehicle; }
    public Slot getAssignedSlot()       { return assignedSlot; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public String getEntryGateId()      { return entryGateId; }

    @Override
    public String toString() {
        return "===== Parking Ticket =====\n"
             + "Ticket ID   : " + ticketId + "\n"
             + "Vehicle     : " + vehicle + "\n"
             + "Entry Gate  : " + entryGateId + "\n"
             + "Slot        : " + assignedSlot.getSlotId()
                               + " (" + assignedSlot.getSlotType() + ")\n"
             + "Entry Time  : " + entryTime + "\n"
             + "==========================";
    }
}