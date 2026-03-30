package com.parkinglot.model;

import com.parkinglot.enums.SlotType;

public class SmallSlot extends Slot {
    public SmallSlot(String slotId, double pricePerHour) {
        super(slotId, SlotType.SMALL, pricePerHour);
    }
}