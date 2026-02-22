import java.util.*;

/**
 * SRP: Single responsibility — orchestrate the cafeteria checkout workflow.
 *
 * Delegates each concern to a dedicated collaborator:
 * - TaxRules → compute tax percentage
 * - DiscountRules → compute discount amount
 * - InvoiceFormatter → build the printable invoice string
 * - InvoiceStore → persist the invoice (interface; does NOT know FileStore)
 */
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store) {
        this.store = store;
        this.formatter = new InvoiceFormatter();
    }

    public void addToMenu(MenuItem item) {
        menu.put(item.id, item);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        // Compute subtotal
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            subtotal += menu.get(l.itemId).price * l.qty;
        }

        // Delegate tax and discount computation
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        BillingResult result = new BillingResult(invId, subtotal, taxPct, tax, discount, total);

        // Delegate formatting
        String printable = formatter.format(invId, lines, menu, result);
        System.out.print(printable);

        // Delegate persistence
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
