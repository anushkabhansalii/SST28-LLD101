# Movie Ticket Booking System - Low Level Design (LLD)

This project is a Java-based **Movie Ticket Booking System** inspired by platforms like **BookMyShow**. It demonstrates a clean low-level design using object-oriented principles and common design patterns.

The system supports:

- City-wise theater management
- Movie and show listing
- Seat management
- Ticket booking
- Payment processing
- Search functionality
- Strategy-based payment handling

---

## Project Structure

```text
Book_My_Show
└── src
    └── com
        └── bookmyshow
            ├── enums
            │   ├── SeatType.java
            │   ├── PaymentStatus.java
            │   ├── PaymentMethod.java
            │   └── BookingStatus.java
            ├── model
            │   ├── City.java
            │   ├── Movie.java
            │   ├── Theater.java
            │   ├── Show.java
            │   ├── Seat.java
            │   ├── GoldSeat.java
            │   ├── PlatinumSeat.java
            │   ├── Booking.java
            │   └── Payment.java
            ├── service
            │   ├── AdminService.java
            │   ├── SearchService.java
            │   └── BookingService.java
            └── strategy
                ├── PaymentStrategy.java
                ├── UPIPaymentStrategy.java
                ├── CardPaymentStrategy.java
                ├── NetBankingPaymentStrategy.java
                └── WalletPaymentStrategy.java
```

---

## Features

- Add cities, theaters, movies, and shows.
- Search shows by city or movie.
- View available seats for a show.
- Book multiple seats for a show.
- Process payment through different payment strategies.
- Maintain booking and payment status.
- Support different seat types like Gold and Platinum.

---

## Core Entities

### 1. City
Represents a city in which theaters are available.

**Responsibilities:**
- Maintain list of theaters in the city.
- Add and retrieve theaters.

---

### 2. Movie
Represents a movie available for booking.

**Typical fields:**
- `movieId`
- `title`
- `language`
- `durationMinutes`

---

### 3. Theater
Represents a theater in a city.

**Responsibilities:**
- Maintain shows running in the theater.
- Associate a theater with a city.

**Typical fields:**
- `theaterId`
- `name`
- `city`
- `shows`

---

### 4. Show
Represents a particular movie show in a theater.

**Responsibilities:**
- Link a movie to a theater and time slot.
- Maintain available seats.
- Return seat availability.

**Typical fields:**
- `showId`
- `movie`
- `theater`
- `startTime`
- `seats`

---

### 5. Seat
Abstract class representing a seat in a show.

**Common fields:**
- `seatId`
- `seatNumber`
- `seatType`
- `price`
- `booked`

**Common behavior:**
- Check availability
- Book seat
- Unbook seat

**Subclasses:**
- `GoldSeat`
- `PlatinumSeat`

---

### 6. Booking
Represents a user booking for a specific show.

**Responsibilities:**
- Hold selected seats
- Maintain booking status
- Calculate total amount
- Link payment with booking

**Typical fields:**
- `bookingId`
- `userId`
- `show`
- `seats`
- `totalAmount`
- `status`
- `payment`

---

### 7. Payment
Represents payment for a booking.

**Responsibilities:**
- Store payment method and status
- Process payment
- Link payment to booking

**Typical fields:**
- `paymentId`
- `booking`
- `amount`
- `method`
- `status`

---

## Service Layer

### AdminService
Used by admin for system setup and configuration.

**Responsibilities:**
- Add movie
- Add theater
- Add show
- Create seats for a show

---

### SearchService
Used for searching available data in the system.

**Responsibilities:**
- Search theaters by city
- Search shows by movie
- Get available seats for a show

---

### BookingService
Handles end-to-end ticket booking flow.

**Responsibilities:**
- Create booking
- Store booking records
- Cancel booking
- Coordinate with payment strategy

---

## Enums Used

### SeatType
```java
GOLD,
PLATINUM
```

### PaymentStatus
```java
PENDING,
COMPLETED,
FAILED
```

### PaymentMethod
```java
UPI,
CARD,
NET_BANKING,
WALLET
```

### BookingStatus
```java
CREATED,
CONFIRMED,
CANCELLED
```

---

## Design Patterns Used

### 1. Strategy Pattern
Used for handling multiple payment methods.

**Interface:**
```java
public interface PaymentStrategy {
    boolean pay(double amount);
}
```

**Implementations:**
- `UPIPaymentStrategy`
- `CardPaymentStrategy`
- `NetBankingPaymentStrategy`
- `WalletPaymentStrategy`

**Why used?**
- Payment processing logic can vary.
- New payment methods can be added without changing booking logic.
- Follows Open/Closed Principle.

---

### 2. Factory Method (possible extension)
Seat creation and payment strategy creation can be abstracted using factory classes.

Example:
```java
Seat seat = SeatFactory.createSeat(SeatType.GOLD, "G1");
PaymentStrategy strategy = PaymentStrategyFactory.create(PaymentMethod.UPI);
```

This helps reduce direct object creation in services.

---

### 3. Service Layer Pattern
Business logic is separated into:
- `AdminService`
- `SearchService`
- `BookingService`

This keeps models lightweight and responsibilities clear.

---

## UML Design Summary

```text
City
 └── List<Theater>

Theater
 └── List<Show>

Show
 ├── Movie
 └── List<Seat>

Seat (abstract)
 ├── GoldSeat
 └── PlatinumSeat

Booking
 ├── Show
 ├── List<Seat>
 └── Payment

Payment
 └── PaymentStrategy

PaymentStrategy (interface)
 ├── UPIPaymentStrategy
 ├── CardPaymentStrategy
 ├── NetBankingPaymentStrategy
 └── WalletPaymentStrategy

Services:
- AdminService
- SearchService
- BookingService
```

---

## Booking Flow

### 1. Admin setup
- Add city
- Add theater
- Add movie
- Add show
- Create seats for show

### 2. User search
- Search theaters by city
- Search shows by movie
- Fetch available seats for selected show

### 3. Booking creation
- User selects seats
- Booking is created
- Total amount is calculated

### 4. Payment
- User chooses payment method
- Corresponding payment strategy is used
- Payment is processed
- Booking status becomes confirmed if successful

---

## Example Flow

```java
City city = new City("C1", "Bangalore", "Karnataka");
Movie movie = new Movie("M1", "Inception", "English", 148);
Theater theater = new Theater("T1", "PVR Forum", city);

Show show = new Show("S1", movie, theater, new Date());
AdminService adminService = new AdminService();
adminService.addShow(theater, show);

SearchService searchService = new SearchService();
List<Seat> seats = searchService.getAvailableSeats(show);

PaymentStrategy strategy = new UPIPaymentStrategy();
BookingService bookingService = new BookingService();
Booking booking = bookingService.createBooking("U1", show, seats.subList(0, 2), strategy);
```

---

## Benefits of This Design

- Modular and easy to extend.
- Clear separation between models, services, and strategies.
- Payment logic is pluggable.
- Easy to add new seat types, payment methods, or search filters.
- Suitable for interview-style LLD discussions and practical learning.

---

## Possible Enhancements

- Add user authentication
- Add coupon/discount support
- Add seat locking with timeout
- Add concurrency handling for simultaneous booking
- Add cancellation and refund flow
- Add movie genres and filters
- Add notifications/email confirmation
- Add theater screens and seat layouts

---

## How to Run

If using packaged structure:

### Compile
```bash
javac -d out src/com/bookmyshow/enums/*.java src/com/bookmyshow/model/*.java src/com/bookmyshow/service/*.java src/com/bookmyshow/strategy/*.java
```

### Run
If you have a main class like `Main.java`:
```bash
java -cp out com.bookmyshow.Main
```

> Replace `Main` with your actual entry-point class name if different.

---

## Conclusion

This **Movie Ticket Booking System** demonstrates a strong low-level design using:
- object-oriented principles
- layered architecture
- Strategy pattern for payment processing
- clean separation of concerns

It is easy to understand, extend, and present in interviews or academic LLD submissions.