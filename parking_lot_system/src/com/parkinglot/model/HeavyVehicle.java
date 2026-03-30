package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

public class HeavyVehicle extends Vehicle {
    public HeavyVehicle(String vehicleNumber) {
        super(vehicleNumber, VehicleType.HEAVY_VEHICLE);
    }
}