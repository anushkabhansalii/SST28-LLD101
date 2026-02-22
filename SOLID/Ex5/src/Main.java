public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        System.out.println("PDF: " + describe(pdf, req));
        System.out.println("CSV: " + describe(csv, req));
        System.out.println("JSON: " + describe(json, req));
    }

    /**
     * LSP proof: no try/catch needed — any Exporter can be used interchangeably.
     * Error vs. success is communicated via ExportResult, not exceptions.
     */
    private static String describe(Exporter e, ExportRequest r) {
        ExportResult out = e.export(r);
        if (out.isError())
            return "ERROR: " + out.errorMessage;
        return "OK bytes=" + out.bytes.length;
    }
}
