/**
 * SRP: Plain data carrier for the results of a billing computation.
 * Separates the "what was calculated" from both the calculation and the
 * formatting.
 */
public class BillingResult {
    public final String invoiceId;
    public final double subtotal;
    public final double taxPercent;
    public final double tax;
    public final double discount;
    public final double total;

    public BillingResult(String invoiceId, double subtotal,
            double taxPercent, double tax,
            double discount, double total) {
        this.invoiceId = invoiceId;
        this.subtotal = subtotal;
        this.taxPercent = taxPercent;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}