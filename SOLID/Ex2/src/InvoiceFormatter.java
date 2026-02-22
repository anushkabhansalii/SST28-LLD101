import java.util.*;

/**
 * SRP: Single responsibility — format an invoice into a printable string.
 * No pricing, no tax, no persistence — only string construction.
 */
public class InvoiceFormatter {

    /**
     * Builds the complete invoice string from the ordered lines (for item rows)
     * and a BillingResult (for the computed totals).
     */
    public String format(String invoiceId, List<OrderLine> lines,
            Map<String, MenuItem> menu, BillingResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(invoiceId).append("\n");
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            sb.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }
        sb.append(String.format("Subtotal: %.2f\n", result.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", result.taxPercent, result.tax));
        sb.append(String.format("Discount: -%.2f\n", result.discount));
        sb.append(String.format("TOTAL: %.2f\n", result.total));
        return sb.toString();
    }
}
