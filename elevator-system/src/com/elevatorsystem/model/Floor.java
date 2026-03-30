package com.elevatorsystem.model;

/**
 * Represents a floor in the building.
 * Holds a reference to the ExternalPanel (hall panel) on that floor.
 */
public class Floor {

    private final int floorNumber;
    private final ExternalPanel externalPanel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.externalPanel = new ExternalPanel(floorNumber);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ExternalPanel getExternalPanel() {
        return externalPanel;
    }

    @Override
    public String toString() {
        return "Floor{number=" + floorNumber + "}";
    }
}