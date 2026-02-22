/**
 * LSP base contract:
 * - export() NEVER throws for any non-null ExportRequest.
 * - export() ALWAYS returns a non-null ExportResult.
 * - On success: result.isError() == false, result.bytes contains the payload.
 * - On failure: result.isError() == true, result.errorMessage describes why.
 *
 * All subclasses MUST honour these pre/postconditions without tightening them.
 */
public abstract class Exporter {
    public abstract ExportResult export(ExportRequest req);
}
