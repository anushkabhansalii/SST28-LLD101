package pen;

// Different refill / ink usage behaviour
public class FountainPen extends Pen {

    public FountainPen(String color, boolean hasCap, boolean hasClip) {
        super("fountain", color, hasCap, hasClip);
    }

    // Fountain pens consume more ink per write
    @Override
    public void write(String s) throws Exception {
        if (!isStarted) {
            throw new Exception("Pen has not been started.");
        }
        if (isClosed) {
            throw new Exception("Cannot write. Pen is closed.");
        }
        if (inkLevel <= 0) {
            throw new Exception("Cannot write. Ink is finished.");
        }

        System.out.println(s + " is being written by a fountain pen in color " + color);
        int consumption = 20; // custom consumption
        inkLevel = Math.max(0, inkLevel - consumption);
        System.out.println("Ink remaining (fountain): " + inkLevel);
    }

    // Different refill rules (example rule)
    @Override
    public void refill(String color) throws Exception {
        if (!isClosed) {
            throw new Exception("Close the fountain pen before refilling.");
        }
        // Example difference: allow only blue/black
        if (!color.equalsIgnoreCase("blue") && !color.equalsIgnoreCase("black")) {
            throw new Exception("Fountain pen only supports blue or black ink.");
        }
        this.inkLevel = 100;
        this.color = color;
        System.out.println("Fountain pen refilled with color: " + color);
    }
}
