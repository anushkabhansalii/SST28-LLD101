package com.elevatorsystem.model;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.DoorStatus;
import com.elevatorsystem.enums.ElevatorStatus;
import com.elevatorsystem.model.Door;
import com.elevatorsystem.model.InternalPanel;
import com.elevatorsystem.model.InternalRequest;
import java.util.TreeSet;

/**
 * Elevator class - The core entity representing a single lift.
 */
public class Elevator {

    private final String id;
    private int currentFloor;
    private Direction currentDirection;
    private ElevatorStatus status;
    private final Door door;
    private final InternalPanel internalPanel;
    private final int maxCapacity;
    private int currentLoad;

    // Stores floors to stop at. TreeSet keeps them sorted for SCAN-like processing.
    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    public Elevator(String id, int maxCapacity) {
        this.id = id;
        this.currentFloor = 0; // Ground floor
        this.currentDirection = Direction.IDLE;
        this.status = ElevatorStatus.IDLE;
        this.door = new Door();
        this.internalPanel = new InternalPanel(this);
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>();
    }

    public void addInternalRequest(InternalRequest request) {
        if (isOverloaded()) {
            triggerAlarm("Elevator overloaded! Cannot accept request.");
            return;
        }

        int targetFloor = request.getFloor();
        if (targetFloor > currentFloor) {
            upRequests.add(targetFloor);
        } else if (targetFloor < currentFloor) {
            downRequests.add(targetFloor);
        }
        
        // If IDLE, decide direction
        if (currentDirection == Direction.IDLE) {
            currentDirection = (targetFloor > currentFloor) ? Direction.UP : Direction.DOWN;
        }
    }

    public void move() {
        if (upRequests.isEmpty() && downRequests.isEmpty()) {
            currentDirection = Direction.IDLE;
            status = ElevatorStatus.IDLE;
            return;
        }

        status = ElevatorStatus.MOVING;
        
        if (currentDirection == Direction.UP) {
            processUpRequests();
        } else if (currentDirection == Direction.DOWN) {
            processDownRequests();
        }
    }

    private void processUpRequests() {
        if (upRequests.isEmpty()) {
            currentDirection = downRequests.isEmpty() ? Direction.IDLE : Direction.DOWN;
            return;
        }
        
        Integer nextFloor = upRequests.ceiling(currentFloor);
        if (nextFloor == null) {
            // No more up requests above current floor, switch direction
            currentDirection = Direction.DOWN;
            return;
        }

        System.out.println("Elevator " + id + " moving UP to floor " + nextFloor);
        currentFloor = nextFloor;
        upRequests.remove(nextFloor);
        stopAtFloor();
    }

    private void processDownRequests() {
        if (downRequests.isEmpty()) {
            currentDirection = upRequests.isEmpty() ? Direction.IDLE : Direction.UP;
            return;
        }

        Integer nextFloor = downRequests.floor(currentFloor);
        if (nextFloor == null) {
            currentDirection = Direction.UP;
            return;
        }

        System.out.println("Elevator " + id + " moving DOWN to floor " + nextFloor);
        currentFloor = nextFloor;
        downRequests.remove(nextFloor);
        stopAtFloor();
    }

    private void stopAtFloor() {
        status = ElevatorStatus.STOPPED;
        door.open();
        // Simulate waiting (in real system, this would be a timer)
        System.out.println("Elevator " + id + " stopping at floor " + currentFloor);
        door.close();
    }

    public void triggerAlarm(String message) {
        System.out.println("[ALARM] Elevator " + id + ": " + message);
    }

    public boolean isOverloaded() {
        return currentLoad >= maxCapacity;
    }

    public String getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getCurrentDirection() { return currentDirection; }
    public ElevatorStatus getStatus() { return status; }
    public InternalPanel getInternalPanel() { return internalPanel; }
    public int getCurrentLoad() { return currentLoad; }
    public void setCurrentLoad(int currentLoad) { this.currentLoad = currentLoad; }

    public void addExternalRequest(int floor, Direction direction) {
        if (direction == Direction.UP) {
            upRequests.add(floor);
        } else {
            downRequests.add(floor);
        }
    }
}