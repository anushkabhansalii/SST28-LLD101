package com.elevatorsystem.display;

import com.elevatorsystem.enums.Direction;

/**
 * Display - Optional component to show current lift status.
 */
public class Display {
    public static void showStatus(String liftId, int floor, Direction direction) {
        System.out.println("[Display-" + liftId + "] Floor: " + floor + " | Direction: " + direction);
    }
}