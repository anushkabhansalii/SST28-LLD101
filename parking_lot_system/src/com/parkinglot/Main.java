package com.parkinglot;

import com.parkinglot.display.DisplayBoard;
import com.parkinglot.model.*;
import com.parkinglot.strategy.*;

/**
 * Main entry point – demonstrates the Parking Lot System.
 *
 * Setup:
 *   • 2 Floors
 *   • Each floor has 3 Small, 3 Medium, 2 Large slots
 *   • 2 Entry gates (GATE-1, GATE-2) – distances set per slot
 *   • 1 Exit gate
 */
public class Main {

    public static void main(String[] args) {

        // 1. Get singleton ParkingLot 
        ParkingLot parkingLot = ParkingLot.getInstance();

        // 2. Create strategies 
        SlotAssignmentStrategy nearestStrategy = new NearestSlotAssignmentStrategy();
        BillingStrategy        billingStrategy = new TypeAndDurationBillingStrategy();

        // 3. Create Entry/Exit gates 
        EntryGate gate1 = new EntryGate("GATE-1", nearestStrategy);
        EntryGate gate2 = new EntryGate("GATE-2", nearestStrategy);
        ExitGate  exit1 = new ExitGate("EXIT-1", billingStrategy);

        parkingLot.addEntryGate(gate1);
        parkingLot.addEntryGate(gate2);
        parkingLot.addExitGate(exit1);

        // 4. Build floors & slots 
        for (int f = 1; f <= 2; f++) {
            Floor floor = new Floor(f);

            // Small slots 
            for (int i = 1; i <= 3; i++) {
                String slotId = "F" + f + "-S" + i;
                SmallSlot slot = new SmallSlot(slotId, 20.0);   // ₹20/hr
                slot.setDistanceFromGate("GATE-1", (f - 1) * 100 + i * 10);
                slot.setDistanceFromGate("GATE-2", (f - 1) * 100 + (4 - i) * 10);
                floor.addSlot(slot);
            }

            // Medium slots 
            for (int i = 1; i <= 3; i++) {
                String slotId = "F" + f + "-M" + i;
                MediumSlot slot = new MediumSlot(slotId, 40.0);  // ₹40/hr
                slot.setDistanceFromGate("GATE-1", (f - 1) * 100 + i * 15);
                slot.setDistanceFromGate("GATE-2", (f - 1) * 100 + (4 - i) * 15);
                floor.addSlot(slot);
            }

            // Large slots 
            for (int i = 1; i <= 2; i++) {
                String slotId = "F" + f + "-L" + i;
                LargeSlot slot = new LargeSlot(slotId, 80.0);   // ₹80/hr
                slot.setDistanceFromGate("GATE-1", (f - 1) * 100 + i * 20);
                slot.setDistanceFromGate("GATE-2", (f - 1) * 100 + (3 - i) * 20);
                floor.addSlot(slot);
            }

            parkingLot.addFloor(floor);
        }

        // 5. Show initial status 
        DisplayBoard board = new DisplayBoard();
        System.out.println(">>> INITIAL STATUS");
        board.showStatus(parkingLot);

        // 6. Park some vehicles 
        Vehicle bike1   = new TwoWheeler("MH-12-AB-1234");
        Vehicle car1    = new Car("MH-14-CD-5678");
        Vehicle truck1  = new HeavyVehicle("MH-01-EF-9090");
        Vehicle car2    = new Car("DL-01-GH-1111");

        ParkingTicket t1 = gate1.generateTicket(bike1,  parkingLot);
        ParkingTicket t2 = gate1.generateTicket(car1,   parkingLot);
        ParkingTicket t3 = gate2.generateTicket(truck1, parkingLot);
        ParkingTicket t4 = gate2.generateTicket(car2,   parkingLot);

        // 7. Show status after parking 
        System.out.println(">>> STATUS AFTER PARKING 4 VEHICLES");
        board.showStatus(parkingLot);

        // 8. Exit a vehicle & generate bill 
        if (t2 != null) {
            Bill bill = exit1.processExit(t2);
        }

        // 9. Show status after exit 
        System.out.println(">>> STATUS AFTER CAR EXITS");
        board.showStatus(parkingLot);
    }
}