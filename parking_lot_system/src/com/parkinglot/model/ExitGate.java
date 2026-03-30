package com.parkinglot.model;

import com.parkinglot.strategy.BillingStrategy;

import java.time.LocalDateTime;

/**
 * Represents an exit gate.
 */
public class ExitGate {
    private final String gateId;
    private final BillingStrategy billingStrategy;

    public ExitGate(String gateId, BillingStrategy billingStrategy) {
        this.gateId = gateId;
        this.billingStrategy = billingStrategy;
    }

    /**
     * Processes the vehicle exit: generates a bill and frees the slot.
     *
     * @param ticket 
     * @return 
     */
    public Bill processExit(ParkingTicket ticket) {
        LocalDateTime exitTime = LocalDateTime.now();
        Bill bill = billingStrategy.generateBill(ticket, exitTime);
        // Free the slot so it can be re-assigned
        ticket.getAssignedSlot().removeVehicle();
        System.out.println("[" + gateId + "] Vehicle exited:\n" + bill);
        return bill;
    }

    public String getGateId() { return gateId; }
}