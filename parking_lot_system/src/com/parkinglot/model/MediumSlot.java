package com.parkinglot.model;

import com.parkinglot.enums.SlotType;

public class MediumSlot extends Slot {
    public MediumSlot(String slotId, double pricePerHour) {
        super(slotId, SlotType.MEDIUM, pricePerHour);
    }
}