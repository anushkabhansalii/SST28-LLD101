package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

public class TwoWheeler extends Vehicle {
    public TwoWheeler(String vehicleNumber) {
        super(vehicleNumber, VehicleType.TWO_WHEELER);
    }
}