import java.nio.charset.StandardCharsets;

/**
 * LSP-compliant: honours the base contract — never throws, always returns
 * ExportResult.
 * When content exceeds the PDF limit, returns an error result instead of
 * throwing.
 */
public class PdfExporter extends Exporter {
    private static final int MAX_BODY_LEN = 20;

    @Override
    public ExportResult export(ExportRequest req) {
        if (req.body != null && req.body.length() > MAX_BODY_LEN) {
            // LSP fix: return error result instead of throwing (precondition not tightened)
            return new ExportResult("application/pdf",
                    "PDF cannot handle content > " + MAX_BODY_LEN + " chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}