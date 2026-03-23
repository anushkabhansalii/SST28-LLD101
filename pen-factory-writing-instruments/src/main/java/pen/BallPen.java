package pen;

// Same refill + write behaviour as gel, but separate class for type
public class BallPen extends Pen {
    public BallPen(String color, boolean hasCap, boolean hasClip) {
        super("ball", color, hasCap, hasClip);
    }
}
