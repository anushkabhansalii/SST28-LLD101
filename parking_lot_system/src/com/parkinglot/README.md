# Parking Lot System - Low Level Design (LLD)

This project is a Java-based **Parking Lot System** designed using clean object-oriented principles and common low-level design patterns. It supports vehicle parking, slot allocation, ticket generation, billing, multiple floors, multiple slot sizes, and pluggable strategies for slot assignment and billing.

---

## Features

- Supports multiple floors.
- Supports multiple slot types:
  - Small
  - Medium
  - Large
- Supports multiple vehicle types:
  - TwoWheeler
  - Car
  - HeavyVehicle
- Entry gate generates parking ticket.
- Exit gate generates bill.
- Slot allocation is strategy-based.
- Billing is strategy-based.
- Display board can show parking availability.
- Uses Singleton for centralized parking lot management.

---

## Project Structure

```text
parking_lot_system
├── README.md
├── LargeSlot.java
├── MediumSlot.java
├── ParkingLot.java
├── ParkingTicket.java
├── Slot.java
├── SmallSlot.java
├── TwoWheeler.java
├── Vehicle.java
├── strategy
│   ├── BillingStrategy.java
│   ├── NearestSlotAssignmentStrategy.java
│   ├── SlotAssignmentStrategy.java
│   └── TypeAndDurationBillingStrategy.java
└── Main.java
```

> Depending on your package structure in VS Code, these files may also be organized into packages like:
> - `model`
> - `strategy`
> - `display`
> - `enums`

---

## LLD Design

The system is composed of the following major entities:

### 1. `ParkingLot`
- Central class of the system.
- Maintains:
  - floors
  - entry gates
  - exit gates
- Implemented as a **Singleton** so the entire system uses only one parking lot instance.

### 2. `Floor`
- Represents a floor inside the parking lot.
- Contains multiple parking slots.

### 3. `Slot`
- Abstract base class for all slot types.
- Common fields:
  - `slotId`
  - `slotType`
  - `price`
  - `isAvailable`
  - `parkedVehicle`
  - distance from entry gates
- Child classes:
  - `SmallSlot`
  - `MediumSlot`
  - `LargeSlot`

### 4. `Vehicle`
- Abstract base class for all vehicles.
- Common fields:
  - `licenseNumber`
  - `vehicleType`
- Child classes:
  - `TwoWheeler`
  - `Car`
  - `HeavyVehicle`

### 5. `EntryGate`
- Used when a vehicle enters the parking lot.
- Uses a `SlotAssignmentStrategy` to allocate the best slot.
- Generates a `ParkingTicket`.

### 6. `ExitGate`
- Used when a vehicle exits the parking lot.
- Uses a `BillingStrategy` to calculate parking charges.
- Generates a `Bill`.

### 7. `ParkingTicket`
- Generated when a vehicle enters.
- Contains:
  - ticket id
  - vehicle
  - slot
  - entry time
  - entry gate details

### 8. `Bill`
- Generated when a vehicle exits.
- Contains:
  - bill id
  - ticket reference
  - exit time
  - total amount

### 9. `DisplayBoard`
- Displays the state of parking slot availability.

---

## Design Patterns Used

### Singleton Pattern
Used in `ParkingLot`.

Why:
- Only one parking lot should exist in the system.
- All floors, gates, and availability should be centrally managed.

Example:
```java
ParkingLot parkingLot = ParkingLot.getInstance();
```

---

### Strategy Pattern
Used for:
- slot assignment
- billing calculation

#### Slot Assignment Strategy
Interface:
```java
public interface SlotAssignmentStrategy {
    Slot assignSlot(Vehicle vehicle, List<Floor> floors);
}
```

Implementation:
```java
public class NearestSlotAssignmentStrategy implements SlotAssignmentStrategy
```

Purpose:
- Finds the nearest suitable slot dynamically.

#### Billing Strategy
Interface:
```java
public interface BillingStrategy {
    Bill generateBill(ParkingTicket ticket, Date exitTime);
}
```

Implementation:
```java
public class TypeAndDurationBillingStrategy implements BillingStrategy
```

Purpose:
- Calculates bill based on vehicle type and parking duration.

---

### Factory Method
The design can also be extended using factory methods for creating:
- vehicles
- slots

This avoids direct object creation everywhere and keeps code clean.

Example idea:
```java
Vehicle vehicle = VehicleFactory.createVehicle(VehicleType.CAR, "KA01AB1234");
```

---

### Observer Pattern (Hinted)
`DisplayBoard` acts like an observer/view layer that reads slot states and displays updated availability.

---

## UML-Style Design Summary

```text
ParkingLot
 ├── List<Floor>
 ├── List<EntryGate>
 ├── List<ExitGate>
 └── getInstance()

Floor
 └── List<Slot>

Slot (abstract)
 ├── SmallSlot
 ├── MediumSlot
 └── LargeSlot

Vehicle (abstract)
 ├── TwoWheeler
 ├── Car
 └── HeavyVehicle

EntryGate
 └── SlotAssignmentStrategy

ExitGate
 └── BillingStrategy

ParkingTicket
Bill
DisplayBoard
```

---

## Core Classes Explanation

### `Vehicle.java`
Abstract parent class for all vehicles.

Typical properties:
```java
String licenseNumber;
VehicleType vehicleType;
```

Child classes:
- `TwoWheeler`
- `Car`
- `HeavyVehicle`

---

### `Slot.java`
Abstract parent class for all slot types.

Typical properties:
```java
String slotId;
SlotType slotType;
boolean isAvailable;
Vehicle parkedVehicle;
double price;
Map<String, Integer> distanceFromEntryGates;
```

Typical methods:
```java
void assignVehicle(Vehicle vehicle);
void removeVehicle();
boolean isAvailable();
```

Child classes:
- `SmallSlot`
- `MediumSlot`
- `LargeSlot`

---

### `ParkingTicket.java`
Represents the parking receipt generated on entry.

Contains:
- ticket id
- vehicle info
- slot assigned
- entry date-time

---

### `Bill.java`
Represents the bill generated on exit.

Contains:
- bill id
- parking ticket
- exit time
- total amount

---

### `EntryGate.java`
Responsible for:
- accepting vehicle entry
- allocating slot using assignment strategy
- creating parking ticket

Pseudo flow:
1. Vehicle comes in.
2. Entry gate asks strategy for best slot.
3. Slot is reserved.
4. Ticket is generated.

---

### `ExitGate.java`
Responsible for:
- processing vehicle exit
- calculating bill
- releasing slot

Pseudo flow:
1. User shows parking ticket.
2. Exit gate calculates duration and amount.
3. Slot is freed.
4. Bill is generated.

---

## Main Flow

The `Main.java` demonstrates the complete flow.

Typical setup:
- create parking lot instance
- create floors
- create slots for each floor
- create strategies
- create gates
- park vehicles
- unpark vehicles
- generate bills
- display slot status

Example high-level flow:

```java
public static void main(String[] args) {
    ParkingLot parkingLot = ParkingLot.getInstance();

    SlotAssignmentStrategy nearestStrategy = new NearestSlotAssignmentStrategy();
    BillingStrategy billingStrategy = new TypeAndDurationBillingStrategy();

    EntryGate gate1 = new EntryGate("GATE-1", nearestStrategy);
    EntryGate gate2 = new EntryGate("GATE-2", nearestStrategy);

    ExitGate exitGate = new ExitGate("EXIT-1", billingStrategy);

    // Add floors and slots
    // Park vehicles
    // Generate tickets
    // Exit vehicles
    // Generate bills
}
```

---

## Example Scenario

Assume:
- 2 floors
- each floor has:
  - 3 small slots
  - 3 medium slots
  - 2 large slots
- 2 entry gates
- 1 exit gate

### Vehicle Entry
- A car enters through `GATE-1`.
- System finds nearest available compatible slot.
- Ticket is generated.

### Vehicle Exit
- Car exits from `EXIT-1`.
- Duration is calculated.
- Bill is generated.
- Slot becomes available again.

---

## Benefits of This Design

- Easy to extend with new vehicle types.
- Easy to extend with new billing strategies.
- Easy to extend with new slot assignment algorithms.
- Follows SOLID principles reasonably well.
- Clear separation of concerns.
- Good interview-style LLD implementation.

---

## How to Run

### Compile
If you are inside the project folder:

```bash
javac -d out *.java strategy/*.java
```

If you are using package folders like `src/com/parkinglot/...`, compile accordingly:

```bash
javac -d out src/com/parkinglot/**/*.java
```

If `**` does not work in your shell, compile package-wise:

```bash
javac -d out src/com/parkinglot/*.java src/com/parkinglot/model/*.java src/com/parkinglot/strategy/*.java src/com/parkinglot/display/*.java src/com/parkinglot/enums/*.java
```

---

### Run

For no-package / flat-file version:

```bash
java -cp out Main
```

For packaged version:

```bash
java -cp out com.parkinglot.Main
```

---

## Future Improvements

- Add factory classes for vehicle and slot creation.
- Add reservation support.
- Add payment modes.
- Add admin dashboard.
- Add real-time display board updates.
- Add different billing for weekdays/weekends.
- Add electric vehicle charging slots.

---

## Conclusion

This Parking Lot System demonstrates a strong **Low Level Design** with proper usage of:
- Singleton
- Strategy
- Factory Method
- Observer-style display handling

It is modular, extensible, and suitable for interview preparation as well as learning object-oriented system design in Java.