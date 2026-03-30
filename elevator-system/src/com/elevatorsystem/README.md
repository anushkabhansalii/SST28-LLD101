# Elevator System - Low Level Design (LLD)

This project is a Java-based **Elevator System** designed using object-oriented principles and common low-level design patterns. It simulates how elevators handle internal and external requests, assign the best elevator, move between floors, and manage door operations.

The design is modular, extensible, and suitable for **LLD interview preparation** as well as understanding real-world system decomposition.

---

## Features

- Supports multiple floors.
- Supports multiple elevators.
- Handles:
  - External requests (Up / Down)
  - Internal requests (floor selection inside elevator)
- Elevator assignment is strategy-based.
- Tracks elevator direction and current state.
- Supports door operations.
- Supports singleton-based building initialization.

---

## Project Structure

```text
elevator_system
├── README.md
└── src
    └── com
        └── elevatorsystem
            ├── controller
            │   └── ElevatorController.java
            ├── display
            ├── enums
            │   ├── Direction.java
            │   ├── ElevatorStatus.java
            │   └── DoorStatus.java
            ├── model
            │   ├── Building.java
            │   ├── Door.java
            │   ├── Elevator.java
            │   ├── ExternalPanel.java
            │   ├── ExternalRequest.java
            │   ├── Floor.java
            │   ├── InternalPanel.java
            │   ├── InternalRequest.java
            │   └── Request.java
            ├── strategy
            │   ├── ElevatorAssignmentStrategy.java
            │   └── NearestIdleStrategy.java
            └── Main.java
```

---

## Core Components

### 1. Building
Represents the entire elevator system.

**Responsibilities:**
- Maintains all floors.
- Maintains all elevators.
- Holds a reference to the central `ElevatorController`.
- Ensures only one instance exists using Singleton pattern.

**Typical fields:**
- `instance`
- `floors`
- `elevators`
- `controller`

---

### 2. Floor
Represents a floor in the building.

**Responsibilities:**
- Stores floor number.
- Contains an `ExternalPanel` for raising elevator requests.

**Typical fields:**
- `floorNumber`
- `externalPanel`

---

### 3. Elevator
Represents an individual elevator.

**Responsibilities:**
- Maintains current floor and direction.
- Stores pending upward and downward requests.
- Moves according to request queue.
- Controls door opening and closing.
- Accepts internal and external requests.

**Typical fields:**
- `id`
- `currentFloor`
- `currentDirection`
- `status`
- `door`
- `internalPanel`
- `upRequests`
- `downRequests`

**Typical methods:**
- `addInternalRequest()`
- `addExternalRequest()`
- `move()`
- `stopAtFloor()`
- `triggerAlarm()`

---

### 4. Request
Abstract base class for all requests.

**Common fields:**
- `floor`
- `isServed`

**Subclasses:**
- `ExternalRequest`
- `InternalRequest`

---

### 5. ExternalRequest
Represents a floor-level request generated outside the elevator.

**Additional field:**
- `requestedDirection`

Example:
- User presses **Up** on 3rd floor.
- User presses **Down** on 7th floor.

---

### 6. InternalRequest
Represents a request created inside the elevator when a passenger selects a destination floor.

---

### 7. Door
Represents the elevator door.

**Responsibilities:**
- Open and close safely.
- Track obstruction status.

**Typical fields:**
- `status`
- `obstructionDetected`

---

### 8. InternalPanel
Panel inside the elevator used to select destination floors.

**Responsibilities:**
- Capture floor selection.
- Forward internal request to the elevator.

---

### 9. ExternalPanel
Panel on each floor used to request an elevator.

**Responsibilities:**
- Raise Up/Down requests.
- Forward request to the `ElevatorController`.

---

### 10. ElevatorController
Central controller responsible for assigning the best elevator.

**Responsibilities:**
- Receive external requests from floors.
- Use assignment strategy to choose the best elevator.
- Forward requests to selected elevator.

**Typical fields:**
- `elevators`
- `strategy`

---

## Strategy Pattern

### ElevatorAssignmentStrategy
Used to determine which elevator should serve a given request.

```java
public interface ElevatorAssignmentStrategy {
    Elevator findBestElevator(List<Elevator> elevators, ExternalRequest request);
}
```

### NearestIdleStrategy
Concrete strategy that selects the nearest suitable idle elevator.

**Why this pattern is useful:**
- Assignment logic can change independently.
- New strategies can be introduced easily.
- Follows Open/Closed Principle.

Possible future strategies:
- MinimumLoadStrategy
- SmartDirectionAwareStrategy
- LeastMovementCostStrategy

---

## Enums Used

### Direction
```java
UP,
DOWN,
IDLE
```

### ElevatorStatus
```java
MOVING,
STOPPED,
IDLE,
MAINTENANCE
```

### DoorStatus
```java
OPEN,
CLOSED
```

---

## Design Patterns Used

### 1. Singleton Pattern
Used in `Building`.

**Why:**
- Only one building instance should manage the elevator system.
- Centralized control of floors and elevators.

Example:
```java
Building building = Building.getInstance(10, 3);
```

---

### 2. Strategy Pattern
Used in elevator assignment logic.

**Why:**
- Different elevator allocation strategies can be plugged in.
- Makes the controller flexible and extensible.

---

## UML Design Summary

```text
Building
 ├── List<Floor>
 ├── List<Elevator>
 └── ElevatorController

Floor
 └── ExternalPanel

Request (abstract)
 ├── ExternalRequest
 └── InternalRequest

Elevator
 ├── Door
 ├── InternalPanel
 ├── upRequests
 └── downRequests

ElevatorController
 └── ElevatorAssignmentStrategy

ElevatorAssignmentStrategy
 └── NearestIdleStrategy
```

---

## Request Handling Flow

### External Request Flow
1. User presses Up or Down on a floor.
2. `ExternalPanel` creates an `ExternalRequest`.
3. Request is sent to `ElevatorController`.
4. Controller uses `ElevatorAssignmentStrategy`.
5. Best elevator is chosen.
6. Elevator serves the request.

---

### Internal Request Flow
1. Passenger enters elevator.
2. Passenger selects destination floor using `InternalPanel`.
3. Elevator stores request in internal queue.
4. Elevator moves accordingly.
5. Elevator stops at the requested floor.

---

## Example Scenario

Assume:
- Building has 10 floors.
- There are 3 elevators.
- A user presses **Up** on floor 4.

Flow:
- `ExternalPanel` on floor 4 sends request.
- `ElevatorController` checks available elevators.
- `NearestIdleStrategy` picks the closest idle elevator.
- Elevator moves to floor 4.
- Passenger enters and selects floor 8.
- `InternalPanel` sends internal request.
- Elevator moves to floor 8 and opens the door.

---

## Benefits of This Design

- Clean separation of responsibilities.
- Extensible strategy-based assignment logic.
- Easy to add more elevator states and scheduling policies.
- Supports realistic modeling of elevator operations.
- Suitable for interviews and academic submissions.

---

## Possible Enhancements

- Add overload detection.
- Add emergency mode.
- Add maintenance lock support.
- Add scheduling optimization for peak hours.
- Add display board for current elevator status.
- Add concurrent request handling.
- Add priority requests for fire/emergency use.
- Add simulation timer and event-driven execution.

---

## How to Run

If using packaged Java structure:

### Compile
```bash
javac -d out src/com/elevatorsystem/enums/*.java src/com/elevatorsystem/model/*.java src/com/elevatorsystem/controller/*.java src/com/elevatorsystem/strategy/*.java src/com/elevatorsystem/Main.java
```

### Run
```bash
java -cp out com.elevatorsystem.Main
```

---

## Example Main Flow

```java
public static void main(String[] args) {
    Building building = Building.getInstance(10, 3);

    ExternalPanel panel = new ExternalPanel(4, building.getController());
    panel.pressUp();

    Elevator elevator = building.getElevators().get(0);
    elevator.getInternalPanel().selectFloor(8);
}
```

---

## Conclusion

This **Elevator System** demonstrates a solid low-level design using:
- object-oriented modeling
- Singleton pattern
- Strategy pattern
- clean controller-based orchestration

It is modular, maintainable, and easy to extend for more advanced elevator scheduling scenarios.