import java.nio.charset.StandardCharsets;

/**
 * LSP-compliant: honours the base contract — never throws, always returns
 * ExportResult.
 * A non-null ExportRequest is always accepted (null fields are safely handled
 * by escape()).
 */
public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // LSP fix: removed dead null-check on req (base contract guarantees non-null
        // req).
        // Null title/body fields are safely handled by escape() below.
        String json = "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null)
            return "";
        return s.replace("\"", "\\\"");
    }
}
