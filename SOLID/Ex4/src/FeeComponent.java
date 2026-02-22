/**
 * OCP: The shared abstraction for every component that contributes to the
 * monthly fee.
 *
 * Implementations include room base prices and add-on prices.
 * Adding a new room type or add-on = new class; HostelFeeCalculator is never
 * edited.
 */
public interface FeeComponent {
    Money monthlyContribution();
}