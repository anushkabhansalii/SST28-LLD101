/**
 * LSP contract: all senders always return a non-null NotificationResult.
 * No sender is permitted to throw instead of returning a result.
 */
public class NotificationResult {
    public final boolean isSuccess;
    public final String errorMessage;
    public final String auditLog;

    private NotificationResult(boolean isSuccess, String errorMessage, String auditLog) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.auditLog = auditLog;
    }

    public static NotificationResult success(String auditLog) {
        return new NotificationResult(true, null, auditLog);
    }

    public static NotificationResult failure(String errorMessage, String auditLog) {
        return new NotificationResult(false, errorMessage, auditLog);
    }
}