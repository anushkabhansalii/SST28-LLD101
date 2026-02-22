import java.util.*;

/**
 * SRP: In-memory invoice store implementation.
 * Implements InvoiceStore so callers depend on the interface, not this class.
 */
public class FileStore implements InvoiceStore {
    private final Map<String, String> files = new HashMap<>();

    @Override
    public void save(String name, String content) {
        files.put(name, content);
    }

    @Override
    public int countLines(String name) {
        String c = files.getOrDefault(name, "");
        if (c.isEmpty())
            return 0;
        return c.split("\n").length;
    }
}
