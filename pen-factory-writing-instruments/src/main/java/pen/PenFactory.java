package pen;

// Factory Method: central place to create any pen
public class PenFactory {

    public static Pen getPen(String type, String color, boolean hasCap, boolean hasClip) throws Exception {
        if (type == null) {
            throw new Exception("Pen type cannot be null.");
        }

        switch (type.toLowerCase()) {
            case "ball":
                return new BallPen(color, hasCap, hasClip);
            case "gel":
                return new GelPen(color, hasCap, hasClip);
            case "fountain":
                return new FountainPen(color, hasCap, hasClip);
            default:
                throw new Exception("Unsupported pen type: " + type);
        }
    }
}
