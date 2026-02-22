/**
 * SRP: Abstracts the invoice persistence concern.
 * CafeteriaSystem depends on this interface, not on FileStore directly.
 */
public interface InvoiceStore {
    void save(String invoiceId, String content);

    int countLines(String invoiceId);
}