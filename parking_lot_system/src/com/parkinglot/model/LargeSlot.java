package com.parkinglot.model;

import com.parkinglot.enums.SlotType;

public class LargeSlot extends Slot {
    public LargeSlot(String slotId, double pricePerHour) {
        super(slotId, SlotType.LARGE, pricePerHour);
    }
}