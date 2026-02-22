/**
 * LSP contract: all exporters always return a non-null ExportResult.
 * On success, isError() == false and bytes contains the export payload.
 * On failure, isError() == true and errorMessage describes the problem.
 * No exporter is permitted to throw instead of returning an error result.
 */
public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final String errorMessage; // null = success

    /** Success result. */
    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.errorMessage = null;
    }

    /** Error result — bytes will be empty, contentType is informational. */
    public ExportResult(String contentType, String errorMessage) {
        this.contentType = contentType;
        this.bytes = new byte[0];
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return errorMessage != null;
    }
}
