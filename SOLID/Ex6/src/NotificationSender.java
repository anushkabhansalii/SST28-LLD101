public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    /**
     * LSP contract:
     * - Must NEVER throw for any non-null Notification.
     * - Must ALWAYS return a non-null NotificationResult.
     */
    public abstract NotificationResult send(Notification n);
}
