package com.parkinglot.model;

import com.parkinglot.enums.SlotType;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single floor in the parking lot.
 */
public class Floor {
    private final int floorNumber;
    private final List<Slot> slots;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    /** Returns number of available slots of a given type on this floor. */
    public long countAvailable(SlotType type) {
        return slots.stream()
                .filter(s -> s.getSlotType() == type && s.isAvailable())
                .count();
    }
}