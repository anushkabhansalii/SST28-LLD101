# Pen Factory & Writing Instruments

This module implements a pen system in Java using design patterns:

- Factory Method for creating pens (`PenFactory`)
- Inheritance / template for shared pen behaviour (`Pen` and subclasses)
- Decorator for adding a grip to any pen (`GripPenDecorator`)

It also demonstrates state handling (start/close), ink consumption, and exceptions.

---

## Project structure

pen-factory-writing-instruments  
└── src  
&ensp;&ensp;└── main  
&ensp;&ensp;&ensp;&ensp;└── java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└── pen  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── WritingInstrument.java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── Pen.java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── BallPen.java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── GelPen.java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── FountainPen.java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── GripPenDecorator.java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── PenFactory.java  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└── PenDemo.java  

---

## Key classes

### WritingInstrument

Interface for all writing instruments:

- `void start()`
- `void write(String s)`
- `void close()`
- `void refill(String color)`

### Pen (abstract)

Base class implementing `WritingInstrument`.

**Attributes**

- `type` – `"ball"`, `"gel"`, `"fountain"`
- `color` – ink color
- `hasCap` – whether the pen has a cap
- `hasClip` – whether the pen has a clip
- `inkLevel` – integer, starts at 100
- `isStarted` – whether the pen is in started state
- `isClosed` – whether the pen is closed

**Behaviour**

- `start()`  
  - Fails if ink is finished or pen is already started/open.  
  - Sets `isStarted = true`, `isClosed = false`.

- `write(String s)`  
  - Checks:
    - pen must be started,  
    - pen must not be closed,  
    - ink must be > 0.  
  - Prints a message and reduces `inkLevel` (10 for ball/gel).

- `close()`  
  - Fails if already closed.  
  - Sets `isClosed = true`, `isStarted = false`.

- `refill(String color)`  
  - Only allowed when pen is closed.  
  - Sets `inkLevel = 100` and updates `color`.

- Getter methods: `getType()`, `getColor()`, `hasCap()`, `hasClip()`, `getInkLevel()`, `isStarted()`, `isClosed()`.

### BallPen

- Extends `Pen`.  
- Constructor: `BallPen(String color, boolean hasCap, boolean hasClip)`.  
- Uses the base `Pen` implementation for write and refill (10 ink per write, normal refill).

### GelPen

- Extends `Pen`.  
- Constructor: `GelPen(String color, boolean hasCap, boolean hasClip)`.  
- Same write/refill behaviour as `BallPen` (same as `Pen`).

### FountainPen

- Extends `Pen`.  
- Constructor: `FountainPen(String color, boolean hasCap, boolean hasClip)`.

**Overrides**

- `write(String s)`  
  - Same validations as base.  
  - Uses more ink per write (20 instead of 10).  
  - Prints: `s is being written by a fountain pen in color <color>`.

- `refill(String color)`  
  - Only allowed when closed.  
  - Accepts only `"blue"` or `"black"`, throws exception for other colors.  
  - Resets `inkLevel = 100` and updates color.

### GripPenDecorator

- Extends `Pen`.  
- Wraps another `Pen` using composition.

**Attributes**

- `innerPen: Pen` – the pen being decorated.

**Behaviour**

- Constructor `GripPenDecorator(Pen p)` assigns `innerPen`.

- `start()`, `close()`, `refill(String color)`  
  - Delegate directly to `innerPen`.

- `write(String s)`  
  - Calls `innerPen.write(s)` so all state checks + ink reduction happen there.  
  - Prints an extra line: `s is being written with a grip`.

This satisfies the requirement: when using grip with a gel pen, you see both:

- `s is being written by a gel pen ...` (from the inner pen)  
- `s is being written with a grip` (from decorator).

### PenFactory

Static factory to create pens:

- Method:  
  `public static Pen getPen(String type, String color, boolean hasCap, boolean hasClip)`

**Behaviour**

- `type` is case‑insensitive.  
- `"ball"` → `BallPen`  
- `"gel"` → `GelPen`  
- `"fountain"` → `FountainPen`  
- Any other type → throws exception.

### PenDemo

Simple `main` class used to run and manually test behaviour and exceptions.

---

## How to compile

From inside the `pen-factory-writing-instruments` folder:

1. Compile all Java files:

   `javac -d out src/main/java/pen/*.java`

2. After success, compiled classes will be under:

   `out/pen/`

---

## How to run the demo

Still inside `pen-factory-writing-instruments`:

Run:

`java -cp out pen.PenDemo`

Expected style of output:

- gel pen workflow
  - `gel pen started.`
  - `Hello is being written by a gel pen in color blue`
  - `Ink remaining: 90`
  - `Hello is being written with a grip`
  - `Anushka is being written by a gel pen in color blue`
  - `Ink remaining: 80`
  - `Anushka is being written with a grip`
  - `gel pen closed.`
  - `Expected error: Pen has not been started.`
  - `gel pen refilled with color: black`
  - `gel pen started.`
  - `Writing after refill is being written by a gel pen in color black`
  - `Ink remaining: 90`
  - `Writing after refill is being written with a grip`

- fountain pen workflow
  - `fountain pen started.`
  - `Fountain writing is being written by a fountain pen in color blue`
  - `Ink remaining (fountain): 80`
  - `fountain pen closed.`
  - `Fountain pen refilled with color: black`
  - `Expected fountain error: Fountain pen only supports blue or black ink.`

(Exact wording can vary if you change messages.)

---

## Requirements checklist

1. **PenFactory.getPen(type, color, withCap, withClip)**  
   - Implemented via `PenFactory.getPen(...)`.  
   - Supports ball, gel, fountain pens.

2. **Methods on pen**  
   - `start`, `write`, `close`, `refill(color)` defined in `WritingInstrument` and implemented in `Pen`/subclasses.

3. **Pen does not start → exception**  
   - `Pen.start()` throws if ink is 0 or pen already started/open.

4. **Ink level starts at 100 and reduces on write**  
   - `inkLevel = 100` in `Pen` constructor.  
   - `Pen.write` subtracts 10; `FountainPen.write` subtracts 20.

5. **Exceptions**  
   - If pen already started, closed, or ink finished, or write before start, appropriate exceptions are thrown.

6. **Ball & Gel same refill behaviour, Fountain different**  
   - `BallPen` and `GelPen` use `Pen.refill`.  
   - `FountainPen` overrides `refill` with its own rule.

7. **Grip adds extra sentence**  
   - `GripPenDecorator.write` prints `s is being written with a grip` in addition to the inner pen’s message.

8. **Extensibility for other writing instruments**  
   - Any new instrument (e.g., `Pencil`) can implement `WritingInstrument` without changing existing classes.

---

## Troubleshooting

- If `javac` says **no matches found for src/main/java/pen/*.java**, make sure:
  - You are inside `pen-factory-writing-instruments`.
  - The path `src/main/java/pen` exists and contains your `.java` files.

- If `java -cp out pen.PenDemo` gives `ClassNotFoundException`:
  - Check `out/pen/PenDemo.class` exists.
  - Confirm the first line of each source file is `package pen;`.
