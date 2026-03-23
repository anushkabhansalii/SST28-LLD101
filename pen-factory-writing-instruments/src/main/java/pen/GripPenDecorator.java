package pen;

// Decorator for adding "grip" behaviour on top of any Pen
public class GripPenDecorator extends Pen {

    private final Pen innerPen;

    public GripPenDecorator(Pen innerPen) {
        super(innerPen.getType(), innerPen.getColor(), innerPen.hasCap(), innerPen.hasClip());
        this.innerPen = innerPen;
    }

    @Override
    public void start() throws Exception {
        innerPen.start();
    }

    @Override
    public void close() throws Exception {
        innerPen.close();
    }

    @Override
    public void write(String s) throws Exception {
        // Let inner pen handle all state + ink exceptions
        innerPen.write(s);

        // Additional behaviour for grip
        System.out.println(s + " is being written with a grip");
    }

    @Override
    public void refill(String color) throws Exception {
        innerPen.refill(color);
    }
}
