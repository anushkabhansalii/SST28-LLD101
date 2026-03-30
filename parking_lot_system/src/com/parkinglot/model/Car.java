package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String vehicleNumber) {
        super(vehicleNumber, VehicleType.CAR);
    }
}