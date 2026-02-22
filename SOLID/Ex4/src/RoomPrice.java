/**
 * OCP: Represents the base monthly price for a specific room type.
 * One instance per room type; no switch required in the calculator.
 */
public class RoomPrice implements FeeComponent {
    private final Money base;

    public RoomPrice(double base) {
        this.base = new Money(base);
    }

    @Override
    public Money monthlyContribution() {
        return base;
    }
}