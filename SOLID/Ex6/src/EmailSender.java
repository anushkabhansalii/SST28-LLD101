public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public NotificationResult send(Notification n) {
        // LSP fix: No more silent truncation. If length is an issue, we could return a
        // failure,
        // but for this demo, we'll just print it as-is to preserve data integrity.
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        String msg = "email sent";
        audit.add(msg);
        return NotificationResult.success(msg);
    }
}
