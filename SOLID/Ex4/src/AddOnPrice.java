/**
 * OCP: Represents the monthly cost of a single add-on service.
 * One instance per add-on; no if/else branching required in the calculator.
 */
public class AddOnPrice implements FeeComponent {
    private final Money price;

    public AddOnPrice(double price) {
        this.price = new Money(price);
    }

    @Override
    public Money monthlyContribution() {
        return price;
    }
}