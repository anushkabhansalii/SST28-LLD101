package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

/**
 * Abstract base class for all vehicles.
 */
public abstract class Vehicle {
    private final String vehicleNumber;
    private final VehicleType vehicleType;

    protected Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return vehicleType + " [" + vehicleNumber + "]";
    }
}