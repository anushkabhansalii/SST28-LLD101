package pen;

public abstract class Pen implements WritingInstrument {
    protected String type;
    protected String color;
    protected boolean hasCap;
    protected boolean hasClip;
    protected int inkLevel;
    protected boolean isStarted;
    protected boolean isClosed;

    // type: ball / gel / fountain
    public Pen(String type, String color, boolean hasCap, boolean hasClip) {
        this.type = type;
        this.color = color;
        this.hasCap = hasCap;
        this.hasClip = hasClip;
        this.inkLevel = 100;      // initial ink
        this.isStarted = false;   // not started yet
        this.isClosed = true;     // starts closed
    }

    @Override
    public void start() throws Exception {
        if (isStarted && !isClosed) {
            throw new Exception("Pen is already started.");
        }
        if (inkLevel <= 0) {
            throw new Exception("Cannot start. Ink is finished.");
        }
        this.isClosed = false;
        this.isStarted = true;
        System.out.println(type + " pen started.");
    }

    @Override
    public void close() throws Exception {
        if (isClosed) {
            throw new Exception("Pen is already closed.");
        }
        this.isClosed = true;
        this.isStarted = false;
        System.out.println(type + " pen closed.");
    }

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

        // basic write behaviour
        System.out.println(s + " is being written by a " + type + " pen in color " + color);

        // reduce ink
        int consumption = 10; // default for ball/gel, can override in subclasses
        inkLevel = Math.max(0, inkLevel - consumption);
        System.out.println("Ink remaining: " + inkLevel);
    }

    @Override
    public void refill(String color) throws Exception {
        if (!isClosed) {
            throw new Exception("Close the pen before refilling.");
        }
        this.inkLevel = 100;
        this.color = color;
        System.out.println(type + " pen refilled with color: " + color);
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public boolean hasCap() {
        return hasCap;
    }

    public boolean hasClip() {
        return hasClip;
    }

    public int getInkLevel() {
        return inkLevel;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
