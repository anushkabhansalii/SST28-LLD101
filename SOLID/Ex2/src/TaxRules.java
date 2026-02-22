/**
 * SRP: Single responsibility — determine the applicable tax percentage
 * for a given customer type. No formatting, no discounts.
 */
public class TaxRules {
    public static double taxPercent(String customerType) {
        if ("student".equalsIgnoreCase(customerType))
            return 5.0;
        if ("staff".equalsIgnoreCase(customerType))
            return 2.0;
        return 8.0;
    }
}
