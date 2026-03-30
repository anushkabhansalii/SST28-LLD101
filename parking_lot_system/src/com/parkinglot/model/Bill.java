package com.parkinglot.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a bill generated on vehicle exit.
 */
public class Bill {
    private final String billId;
    private final ParkingTicket ticket;
    private final LocalDateTime exitTime;
    private final double totalAmount;

    public Bill(ParkingTicket ticket, LocalDateTime exitTime, double totalAmount) {
        this.billId = "BILL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.totalAmount = totalAmount;
    }

    public String getBillId()             { return billId; }
    public ParkingTicket getTicket()      { return ticket; }
    public LocalDateTime getExitTime()    { return exitTime; }
    public double getTotalAmount()        { return totalAmount; }

    @Override
    public String toString() {
        return "===== Parking Bill =====\n"
             + "Bill ID     : " + billId + "\n"
             + "Ticket ID   : " + ticket.getTicketId() + "\n"
             + "Vehicle     : " + ticket.getVehicle() + "\n"
             + "Slot Type   : " + ticket.getAssignedSlot().getSlotType() + "\n"
             + "Entry Time  : " + ticket.getEntryTime() + "\n"
             + "Exit Time   : " + exitTime + "\n"
             + "Total Amount: ₹" + String.format("%.2f", totalAmount) + "\n"
             + "========================";
    }
}