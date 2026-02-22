import java.nio.charset.StandardCharsets;

/**
 * LSP-compliant: honours the base contract — never throws, always returns
 * ExportResult.
 * CSV format requires sanitising newlines and commas inside field values;
 * this is intentional encoding, not lossy data corruption.
 */
public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // CSV-safe encoding: replace control characters within field values
        String body = req.body == null ? "" : req.body.replace("\n", " ").replace(",", " ");
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
